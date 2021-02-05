public abstract class BaseException extends Exception{

    public String message;

    public BaseException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
