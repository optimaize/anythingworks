package com.optimaize.anythingworks.client.soap.exensions.exceptiontranslation;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.anythingworks.client.soap.exception.*;
import com.optimaize.anythingworks.common.soap.exception.Blame;
import com.optimaize.anythingworks.common.soap.exception.RetryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Translates exceptions that arrive in the form of generated wsdl exception classes from the server.
 *
 * @author Fabian Kessler
 */
public class DefaultClientExceptionTranslator implements ExceptionTranslator {

    private static final Map<String,Class<? extends ServiceException>> mappings = new HashMap<>();
    static {
        mappings.put("InvalidInputWebServiceException", InvalidInputServiceException.class);
        mappings.put("AccessDeniedWebServiceException", AccessDeniedServiceException.class);
        mappings.put("InternalServerErrorWebServiceException",     InternalServiceException.class);
        //mappings.put("ServiceUnavailableWebServiceException",     .class); //*shrug*
    }


    public boolean canTranslate(@NotNull Throwable t) {
        if (t instanceof Exception) {
            if (mappings.containsKey(t.getClass().getSimpleName())) {
                return (findGetFaultInfoMethod(t)) != null;
            }
        }
        return false;
    }


    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws Exception {
        String className = t.getClass().getSimpleName();
        String msg = t.getMessage();
        FaultInfo faultInfo = extractFaultInfo(t);
        switch (className) {
            case "InvalidInputWebServiceException":
                throw new InvalidInputServiceException(msg, faultInfo);
            case "AccessDeniedWebServiceException":
                throw new AccessDeniedServiceException(msg, faultInfo);
            case "InternalServerErrorWebServiceException":
                throw new InternalServiceException(msg, faultInfo);
            default:
                throw new UnsupportedOperationException("Unhandled case, or canTranslate() not called: "+className+"!");
        }
    }

    @NotNull
    private FaultInfo extractFaultInfo(Throwable t) {
        Method getFaultInfoMethod = findGetFaultInfoMethod(t);
        if (getFaultInfoMethod==null) throw new AssertionError("Was canTranslate() called!?!");
        return translateFaultBean(execMethod(t, getFaultInfoMethod));
    }


    @NotNull
    private FaultInfo translateFaultBean(Object oFaultBean) {
        int errorCode = extractErrorCode(oFaultBean);
        Blame blame           = extractBlame(oFaultBean);
        String faultCause = extractFaultCause(oFaultBean);
        String message = extractMessage(oFaultBean);
        Retry retrySameServer       = extractRetrySameServer(oFaultBean);
        Retry retryOtherServers     = extractRetryOtherServers(oFaultBean);
        boolean problemReported = extractProblemReported(oFaultBean); //TODO decide nullablility

        return new FaultInfo(errorCode, blame, faultCause, message, retrySameServer, retryOtherServers, problemReported);
    }

    private int extractErrorCode(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getErrorCode");
        Object o = execMethod(oFaultBean, m);
        return (Integer)o;
    }

    @NotNull
    private String extractFaultCause(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getFaultCause");
        return execMethod(oFaultBean, m).toString();
    }

    @NotNull
    private String extractMessage(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getMessage");
        return execMethod(oFaultBean, m).toString();
    }

    @NotNull
    private Blame extractBlame(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getBlame");
        Object oBlame = execMethod(oFaultBean, m);
        return Blame.valueOf(oBlame.toString());
    }
    @NotNull
    private Retry extractRetrySameServer(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getRetrySameServer");
        return handleRetryExtraction(oFaultBean, m);
    }

    @NotNull
    private Retry extractRetryOtherServers(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getRetryOtherServers");
        return handleRetryExtraction(oFaultBean, m);
    }

    private Retry handleRetryExtraction(Object oFaultBean, Method m) {
        //gives us THE or just A retry object.
        Object oRetry = execMethod(oFaultBean, m);

        //we can't be sure which it is, therefore we go on with generics, not type casting.
        Method m1 = expectMethod(oRetry, "getRetryType");
        Method m2 = expectMethod(oRetry, "getRetryInSeconds");
        return new Retry(RetryType.valueOf(execMethod(oRetry, m1).toString()), (Long)execMethod(oRetry, m2));
    }
    @Nullable
    private Boolean extractProblemReported(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "isProblemReported");
        Object problemLogged = execMethod(oFaultBean, m);
        return (Boolean)problemLogged;
    }




    @Nullable
    private Method findGetFaultInfoMethod(@NotNull Throwable t) {
        String methodName = "getFaultInfo";
        return findMethod(t, methodName);
    }

    @Nullable
    private Method findMethod(Object o, String methodName) {
        Method m[] = o.getClass().getDeclaredMethods();
        for (Method method : m) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }
    @Nullable
    private Method expectMethod(Object o, String methodName) {
        Method method = findMethod(o, methodName);
        if (method==null) throw new AssertionError("Missing method: "+methodName+"!");
        return method;
    }

    private Object execMethod(@NotNull Object o, @NotNull Method getFaultInfoMethod) {
        try {
            return getFaultInfoMethod.invoke(o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }

}
