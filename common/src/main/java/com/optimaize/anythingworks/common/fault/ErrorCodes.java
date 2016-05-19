package com.optimaize.anythingworks.common.fault;

import com.google.common.base.Optional;

/**
 * A non-complete, non-authoritative list of error codes suggested by soapworks.
 *
 * <p>Users can create additional enums with more codes to extend these, and are also free to completely
 * ignore these codes here.</p>
 *
 * @author Fabian Kessler
 */
public class ErrorCodes {

    public enum Client {
        CLIENT_ERROR(1000),


        //BAD REQUEST: 1100-1199
        BAD_REQUEST(1100),
        NO_SUCH_SERVICE(1110),
        UNSUPPORTED_TECHNOLOGY(1111), //eg XML or JSON, or SOAP or REST
        UNSUPPORTED_API_VERSION(1112),

        INVALID_INPUT(1120),
        MARSHALLING_FAILED(1122),


        //ACCESS DENIED: 1200-1299
        PERMISSION_DENIED(1200),

        ACCOUNT_UNKNOWN(1201),

        /**
         * Disabled, suspended, expired, not active yet.
         */
        ACCOUNT_INACTIVE(1210),
        ACCOUNT_EXPIRED(1211),
        ACCOUNT_NOTACTIVEYET(1212),

        QUOTA(1220),
        REQUEST_LIMIT_REACHED(1221),
        TOO_MANY_CONCURRENT_REQUESTS(1222),

        HOST(1230),
        HOST_BLOCKED(1231),

        SERVICE(1240),
        SERVICE_NOT_ALLOWED(1241),


        //RESPONSE: 1300-1399
        FAILED_HANDLING_RESPONSE(1300),
        UNMARSHALLING_FAILED(1301),

        ;

        private final int code;

        Client(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static Optional<Client> fromCode(int code) {
            for (Client entry : values()) {
                if (entry.getCode() == code) {
                    return Optional.of(entry);
                }
            }
            return Optional.absent();
        }
    }


    public enum Server {
        SERVER_ERROR(2000),

        //
        INTERNAL_SERVER_ERROR(2100),

        //
        SERVICE_TEMPORARILY_UNAVAILABLE(2200),

        BAD_RESPONSE(2300),

        ;

        private final int code;

        Server(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static Optional<Server> fromCode(int code) {
            for (Server entry : values()) {
                if (entry.getCode() == code) {
                    return Optional.of(entry);
                }
            }
            return Optional.absent();
        }
    }



    public enum Network {
        NETWORK_ERROR(3000),

        ;

        private final int code;

        Network(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static Optional<Network> fromCode(int code) {
            for (Network entry : values()) {
                if (entry.getCode() == code) {
                    return Optional.of(entry);
                }
            }
            return Optional.absent();
        }
    }


//    public enum Protocol {
//        PROTOCOL(4000),
//
//        ;
//
//        private final int code;
//
//        Protocol(int code) {
//            this.code = code;
//        }
//
//        public int getCode() {
//            return code;
//        }
//
//        public static Optional<Protocol> fromCode(int code) {
//            for (Protocol entry : values()) {
//                if (entry.getCode() == code) {
//                    return Optional.of(entry);
//                }
//            }
//            return Optional.absent();
//        }
//    }




//    public enum Unknown {
//        UNKNOWN_ERROR(9000),
//
//        ;
//
//        private final int code;
//
//        Unknown(int code) {
//            this.code = code;
//        }
//
//        public int getCode() {
//            return code;
//        }
//
//        public static Optional<Unknown> fromCode(int code) {
//            for (Unknown entry : values()) {
//                if (entry.getCode() == code) {
//                    return Optional.of(entry);
//                }
//            }
//            return Optional.absent();
//        }
//    }

}
