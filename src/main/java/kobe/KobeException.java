package kobe;

abstract class KobeException extends RuntimeException {

    private String errMsg;

    /**
     * Constructor for all exceptions pertaining to Kobe.
     *
     * @param errMsg
     */
    public KobeException(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * Gets the error message
     *
     * @return  the corresponding error message
     */
    //Getter
    public String getMessage() {
        return errMsg;
    }

}