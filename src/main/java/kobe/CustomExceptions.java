package kobe;

class CustomExceptions {

    //for incomplete commands
    public static class IncompleteDecriptionException extends KobeException {
        //        public IncompleteDecriptionException(String errMsg, Throwable cause) {
        /**
         * Throws an exception for incomplete command descriptions
         *
         * @param errMsg  the error message
         */
        public IncompleteDecriptionException(String errMsg) {
            super(errMsg);
//            super(errMsg, cause);
        }
    }

    //for gibberish commands
    public static class IncorrectDecriptionException extends KobeException {
        //        public IncorrectDecriptionException(String errMsg, Throwable cause) {
        /**
         * Throws an exception for incorrect command descriptions that cannot be processed
         *
         * @param errMsg  the error message
         */
        public IncorrectDecriptionException(String errMsg) {
            super(errMsg);
//            super(errMsg, cause);
        }
    }

}