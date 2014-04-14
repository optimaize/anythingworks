package com.optimaize.soapworks.client.exensions.exceptiontranslation;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.soapworks.client.exception.*;
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
                throw new AssertionError("Unhandled case, or canTranslate() not called: "+className+"!");
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
        Blame blame           = extractBlame(oFaultBean);
        Retry retry           = extractRetry(oFaultBean);
        Boolean problemLogged = extractProblemLogged(oFaultBean);
        return new FaultInfo(blame, retry, problemLogged);
    }

    @NotNull
    private Blame extractBlame(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getBlame");
        Object oBlame = execMethod(oFaultBean, m);
        return Blame.valueOf(oBlame.toString());
    }
    @NotNull
    private Retry extractRetry(Object oFaultBean) {
//        //TODO there are 2 variables: retrySameServer and retryOtherServers. decide what to do. maybe rename server to address or location.
//        //TODO it's an object now, not a simple enum.
//        Method m = expectMethod(oFaultBean, "getRetrySameServer"); //getRetry
//        Object oRetry = execMethod(oFaultBean, m);
//        return Retry.valueOf(oRetry.toString());

        return Retry.UNKNOWN; //FIXME
    }
    @Nullable
    private Boolean extractProblemLogged(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "isProblemReported"); // isProblemLogged
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
