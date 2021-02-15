public class Response {

    private final ResponseStatus status;
    private final String message;

    public Response(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
