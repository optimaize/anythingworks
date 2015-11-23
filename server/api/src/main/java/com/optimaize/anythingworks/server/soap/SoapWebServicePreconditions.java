package com.optimaize.anythingworks.server.soap;


import com.optimaize.anythingworks.common.fault.exceptions.BadRequestServiceException;

/**
 * Like Google's Preconditions class it checks user input for validity and stupidity.
 * It throws {@link BadRequestServiceException} in case of error.
 */
@SuppressWarnings({"JavaDoc"})
public class SoapWebServicePreconditions {

    private SoapWebServicePreconditions() {
        throw new Error("No instance!");
    }

//    public static <T> T notNull(T o) throws BadRequestWebServiceException {
//        return notNull(o, null);
//    }
//    public static <T> T notNull(T o, @Nullable String varName) throws BadRequestWebServiceException {
//        if (o == null) {
//            String msg = "Variable ";
//            if (varName!=null) msg += varName;
//            msg += " was null!";
//            throw new BadRequestServiceException(msg);
//        }
//        return o;
//    }
//
//    /**
//     * Checks that the string is either null, or not empty.
//     */
//    public static String notEmpty(String s) throws BadRequestWebServiceException {
//        return notEmpty(s, null);
//    }
//    public static String notEmpty(String s, @Nullable String varName) throws BadRequestWebServiceException {
//        if (s == null) return s;
//        if (s.isEmpty()) {
//            String msg = "Variable ";
//            if (varName!=null) msg += varName;
//            msg += " was empty!";
//            throw new BadRequestWebServiceException(msg);
//        }
//        return s;
//    }
//
//    /**
//     * Checks that the string is neither null nor empty.
//     */
//    public static String notNullAndNotEmpty(String s) throws BadRequestWebServiceException {
//        return notNullAndNotEmpty(s, null);
//    }
//    public static String notNullAndNotEmpty(String s, @Nullable String varName) throws BadRequestWebServiceException {
//        notNull(s, varName);
//        notEmpty(s, varName);
//        return s;
//    }
//
//    public static String min(String s, int min, String varName) throws BadRequestWebServiceException {
//        if (s==null) return s;
//        if (s.length() < min) {
//            String msg = "Variable ";
//            if (varName!=null) msg += varName;
//            msg += " too short, min="+min+" but was "+s.length()+", value was >>>"+s+"<<<!";
//            throw new BadRequestWebServiceException(msg);
//        }
//        return s;
//    }
//    public static String max(String s, int max) throws BadRequestWebServiceException {
//        return max(s, max, null);
//    }
//    public static String max(String s, int max, @Nullable String varName) throws BadRequestWebServiceException {
//        if (s==null) return s;
//        if (s.length() > max) {
//            String msg = "Variable ";
//            if (varName!=null) msg += varName;
//            msg += " too long, max="+max+" but was "+s.length()+", value was >>>"+s+"<<<!";
//            throw new BadRequestWebServiceException(msg);
//        }
//        return s;
//    }
//    public static String length(String s, int min, int max, String varName) throws BadRequestWebServiceException {
//        if (s==null) return s;
//        min(s, min, varName);
//        max(s, max, varName);
//        return s;
//    }

}
