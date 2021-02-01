package kobe;

abstract class KobeException extends RuntimeException {

    private String errMsg;
//    private Throwable cause;

    /**
     * Constructor for all exceptions pertaining to Kobe.
     *
     * @param errMsg
     */
    public KobeException(String errMsg) {
        this.errMsg = errMsg;
    }

//    public KobeException(String errMsg, Throwable cause) {
//        this.errMsg = errMsg;
//        this.cause = cause;
//    }

    /**
     * Gets the error message
     *
     * @return  the corresponding error message
     */
    //Getter
    public String getMessage() {
        return errMsg;
    }

//    //Getter
//    public Throwable getCause() {
//        return cause;
//    }

}