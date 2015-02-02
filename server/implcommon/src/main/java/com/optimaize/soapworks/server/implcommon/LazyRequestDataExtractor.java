package com.optimaize.soapworks.server.implcommon;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.sun.xml.ws.api.server.WebServiceContextDelegate;
import com.sun.xml.ws.server.AbstractWebServiceContext;
import org.jetbrains.annotations.NotNull;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Use this if you work in single-threaded mode, and you are sure that this data is only requested
 * by the same Thread that accepted the request from the web service method.
 *
 * All methods throw IllegalStateException if called from a Thread other than the one that
 * accepted the request.
 *
 */
@Beta
public class LazyRequestDataExtractor implements RequestDataExtractor {

    private final WebServiceContext webServiceContext;
    private final MessageContext messageContext;

    public LazyRequestDataExtractor(WebServiceContext webServiceContext) {
        this.webServiceContext = webServiceContext;
        try {
            //according to my measuring this call is cheap. (would be nice to look into the source,
            //maybe it's just a simple getter?
            messageContext = webServiceContext.getMessageContext();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Only the Thread accepting the request has access to this!", e);
        }
    }


    /**
     * @return May be empty. Does not contain empty strings (they are skipped).
     */
    @NotNull
    public List<String> requestHeader(@NotNull String name) throws IllegalStateException {
        List list = (List) ((Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get(name);
        List<String> ret = new ArrayList<>(list.size());
        for (Object o : list) {
            String s = (String) o;
            if (!s.isEmpty()) {
                ret.add(s);
            }
        }
        return ret;
    }

    /**
     * From http request headers. Returning the first. Absent if there is none or empty string.
     */
    @NotNull
    public Optional<String> firstRequestHeader(@NotNull String name) throws IllegalStateException {
        List<String> list = requestHeader(name);
        if (list.isEmpty()) return Optional.absent();
        return Optional.of(list.get(0));
    }


    @Override
    @NotNull
    public Optional<String> requestHeaderUserAgent() throws IllegalStateException {
        return firstRequestHeader("user-agent");
    }

    @Override
    @NotNull
    public Optional<String> requestHeaderContentType() throws IllegalStateException {
        return firstRequestHeader("content-type");
    }

    @Override
    @NotNull
    public Optional<String> requestHeaderAccept() throws IllegalStateException {
        return firstRequestHeader("accept");
    }

    @Override
    @NotNull
    public Optional<String> requestHeaderHost() throws IllegalStateException {
        return firstRequestHeader("host");
    }

    @Override
    @NotNull
    public Optional<String> requestHeaderConnection() throws IllegalStateException {
        return firstRequestHeader("connection");
    }


    @Override
    @NotNull
    public String requestMethod() throws IllegalStateException {
        return (String)messageContext.get(MessageContext.HTTP_REQUEST_METHOD);
    }

    @Override
    public boolean wasTransportSecure() throws IllegalStateException {
        return ((AbstractWebServiceContext) webServiceContext).getRequestPacket().wasTransportSecure;
    }

    @Override
    @NotNull
    public String uri() throws IllegalStateException {
        WebServiceContextDelegate webServiceContextDelegate = ((AbstractWebServiceContext) webServiceContext).getRequestPacket().webServiceContextDelegate;
        Class<?> c = webServiceContextDelegate.getClass();
        Field field;
        try {
            field = c.getDeclaredField("request");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(true);
        Object result;
        try {
            result = field.get(webServiceContextDelegate);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        c = result.getClass();
        try {
            field = c.getDeclaredField("request");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(true);
        Object request;
        try {
            request = field.get(result);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        java.lang.reflect.Method method;
        try {
            method = request.getClass().getMethod("getRequestURI");
        } catch (SecurityException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Object uri;
        try {
            uri = method.invoke(request);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        if (!(uri instanceof String)) {
            throw new RuntimeException("Not a string!");
        }
        return (String)uri;
    }

}
