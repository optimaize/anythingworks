package com.optimaize.soapworks.server.exception;

/**
 * A non-complete, non-authoritative list of error codes suggested by soapworks.
 *
 * <p>Users can create additional enums with more codes to extend these, and are also free to completely
 * ignore these codes here.</p>
 *
 * @author Fabian Kessler
 */
public class ErrorCodes {

    public static enum Client {
        CLIENT_ERROR(2000),

        PERMISSION_DENIED(2100),
        ACCOUNT_UNKNOWN(2101),
        /**
         * Disabled, suspended, expired, not active yet.
         */
        ACCOUNT_INACTIVE(2102),
        REQUEST_LIMIT_REACHED(2120),
        TOO_MANY_CONCURRENT_REQUESTS(2121),
        HOST_BLOCKED(2140),

        INVALID_INPUT(2200),
        ;

        private final int code;

        Client(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }


    public static enum Server {
        SERVER_ERROR(1000),
        SERVICE_TEMPORARILY_UNAVAILABLE(1100),

        ;

        private final int code;

        Server(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }



    public static enum Network {
        NETWORK_ERROR(3000),

        ;

        private final int code;

        Network(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }


    public static enum Unknown {
        UNKNOWN_ERROR(4000),

        ;

        private final int code;

        Unknown(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }


}
