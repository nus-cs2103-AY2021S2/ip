package kobe;

abstract class KobeException extends RuntimeException {

    private String errMsg;
//    private Throwable cause;

    public KobeException(String errMsg) {
        this.errMsg = errMsg;
    }

//    public KobeException(String errMsg, Throwable cause) {
//        this.errMsg = errMsg;
//        this.cause = cause;
//    }

    //Getter
    public String getMessage() {
        return errMsg;
    }

//    //Getter
//    public Throwable getCause() {
//        return cause;
//    }

}