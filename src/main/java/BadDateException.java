public class BadDateException extends Exception {
    @Override
    public String getMessage() {
        return "Please format your dead as yyyy-mm-dd";
    }
}