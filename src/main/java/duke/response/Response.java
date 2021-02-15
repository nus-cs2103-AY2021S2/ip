package duke.response;

public class Response {
    private String text;
    private ResponseStatus status;

    public Response(String text, ResponseStatus status) {
        this.text = text;
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public ResponseStatus getStatus() {
        return status;
    }
}
