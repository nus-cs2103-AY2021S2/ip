package kobe;

class CustomExceptions {

    //Class for incomplete commands
    public static class IncompleteDecriptionException extends KobeException {
        /**
         * Throws an exception for incomplete command descriptions
         *
         * @param errMsg  the error message
         */
        public IncompleteDecriptionException(String errMsg) {
            super(errMsg);
        }
    }

    //Class for gibberish commands
    public static class IncorrectDecriptionException extends KobeException {
        /**
         * Throws an exception for incorrect command descriptions that cannot be processed
         *
         * @param errMsg  the error message
         */
        public IncorrectDecriptionException(String errMsg) {
            super(errMsg);
        }
    }

}