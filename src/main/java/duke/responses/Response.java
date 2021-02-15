package duke.responses;

public class Response {

    private final ResponseStatus status;
    private final String message;

    private Response(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    private Response(ResponseStatus status, String[] messages) {
        this(status, String.join("\n", messages));
    }

    public static Response createResponseOk(String message) {
        return new Response(ResponseStatus.OK, message);
    }
    public static Response createResponseOk(String ... messages) {
        return new Response(ResponseStatus.OK, messages);
    }

    public static Response createResponseBad(String message) {
        return new Response(ResponseStatus.BAD, message);
    }

    public static Response createResponseExit() {
        return new Response(ResponseStatus.EXIT, "");
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
