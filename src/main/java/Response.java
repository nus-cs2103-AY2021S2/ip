public class Response {

    public ResponseStatus status;
    public String message;

    Response(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
