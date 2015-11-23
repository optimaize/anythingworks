package com.optimaize.anythingworks.client.soap.exensions.exceptiontranslation;

import com.optimaize.anythingworks.client.soap.exception.SoapFaultInfo;
import com.optimaize.anythingworks.common.fault.exceptions.*;
import com.optimaize.anythingworks.common.fault.faultinfo.Blame;
import com.optimaize.anythingworks.common.fault.faultinfo.Retry;
import com.optimaize.anythingworks.common.fault.faultinfo.RetryType;
import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Translates exceptions that arrive in the form of generated wsdl exception classes from the server.
 *
 * @author Fabian Kessler
 */
public class DefaultClientExceptionTranslator implements ExceptionTranslator {

//    private static final Map<String,Class<? extends ServiceException>> mappings = new HashMap<>();
//    static {
//        mappings.put("SoapWebServiceException", SoapWebServiceException.class);
//
////        mappings.put("BadRequestWebServiceException", BadRequestServiceException.class);
////        mappings.put("AccessDeniedWebServiceException", AccessDeniedServiceException.class);
////        mappings.put("ProtocolWebServiceException", ProtocolServiceException.class);
////
////        mappings.put("InternalServerErrorWebServiceException",     InternalServerErrorServiceException.class);
////        mappings.put("ServiceTemporarilyUnavailableWebServiceException",     ServiceTemporarilyUnavailableServiceException.class);
////        mappings.put("BadResponseWebServiceException",     BadResponseServiceException.class);
//    }


    public boolean canTranslate(@NotNull Throwable t) {
        if (t instanceof Exception) {
            if ("SoapWebServiceException".equals(t.getClass().getSimpleName())) {
                return (findGetFaultInfoMethod(t)) != null;
            }
        }
        return false;
    }


    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws Exception {
        SoapFaultInfo faultInfo = extractFaultInfo(t);
        switch (faultInfo.getFaultCause()) {
            case "BadRequest":
                throw new BadRequestServiceException(faultInfo);
            case "AccessDenied":
                throw new AccessDeniedServiceException(faultInfo);
            case "Protocol":
                throw new ProtocolServiceException(faultInfo);

            case "InternalServerError":
                throw new InternalServerErrorServiceException(faultInfo);
            case "ServiceTemporarilyUnavailable":
                throw new ServiceTemporarilyUnavailableServiceException(faultInfo);
            case "BadResponse":
                throw new BadResponseServiceException(faultInfo);

            default:
                throw new UnsupportedOperationException("Unhandled case: "+faultInfo.getFaultCause()+"!");
        }
    }

    @NotNull
    private SoapFaultInfo extractFaultInfo(Throwable t) {
        Method getFaultInfoMethod = findGetFaultInfoMethod(t);
        if (getFaultInfoMethod==null) throw new AssertionError("Was canTranslate() called!?!");
        return translateFaultBean(execMethod(t, getFaultInfoMethod));
    }


    @NotNull
    private SoapFaultInfo translateFaultBean(Object oFaultBean) {
        String faultCause = extractFaultCause(oFaultBean);
        Blame blame           = extractBlame(oFaultBean);
        String message = extractMessage(oFaultBean);
        String applicationErrorCode = extractApplicationErrorCode(oFaultBean);
        String incidentId = extractIncidentId(oFaultBean);
        Retry retrySameLocation       = extractRetrySameLocation(oFaultBean);
        Retry retryOtherLocations     = extractRetryOtherLocations(oFaultBean);

        return new SoapFaultInfo(faultCause, blame, message, applicationErrorCode, incidentId, retrySameLocation, retryOtherLocations);
    }

    @NotNull
    private String extractFaultCause(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getFaultCause");
        return execMethod(oFaultBean, m).toString();
    }

    @NotNull
    private Blame extractBlame(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getBlame");
        Object oBlame = execMethod(oFaultBean, m);
        return Blame.valueOf(oBlame.toString());
    }

    @NotNull
    private String extractMessage(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getMessage");
        return execMethod(oFaultBean, m).toString();
    }

    @Nullable
    private String extractApplicationErrorCode(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getApplicationErrorCode");
        Object value = execMethod(oFaultBean, m);
        return (String)value;
    }
    @Nullable
    private String extractIncidentId(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getIncidentId");
        Object value = execMethod(oFaultBean, m);
        return (String)value;
    }

    @Nullable
    private Retry extractRetrySameLocation(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getRetrySameLocation");
        return handleRetryExtraction(oFaultBean, m);
    }
    @Nullable
    private Retry extractRetryOtherLocations(Object oFaultBean) {
        Method m = expectMethod(oFaultBean, "getRetryOtherLocations");
        return handleRetryExtraction(oFaultBean, m);
    }
    private Retry handleRetryExtraction(Object oFaultBean, Method m) {
        //gives us THE or just A retry object.
        Object oRetry = execMethod(oFaultBean, m);
        if (oRetry==null) return null;

        //we can't be sure which it is, therefore we go on with generics, not type casting.
        Method m1 = expectMethod(oRetry, "getRetryType");
        Method m2 = expectMethod(oRetry, "getRetryInSeconds");
        return new Retry(RetryType.valueOf(execMethod(oRetry, m1).toString()), (Long)execMethod(oRetry, m2));
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
