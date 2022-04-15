package duke.responses;

/**
 * Association class between GUI controller and Duke.
 *
 * Response class written because of the need to shovel the error messages to the GUI
 * as well, which required tagging of responses by Duke.
 */
public class Response {

    private final ResponseStatus status;
    private final String message;

    private Response(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    /** For easy multiline string concatenation */
    private Response(ResponseStatus status, String[] messages) {
        this(status, String.join("\n", messages));
    }

    /** Constructor with responseType defaulted to OK */
    public static Response createResponseOk(String message) {
        return new Response(ResponseStatus.OK, message);
    }

    /** Constructor with responseType defaulted to OK */
    public static Response createResponseOk(String ... messages) {
        return new Response(ResponseStatus.OK, messages);
    }

    /** Constructor with responseType defaulted to BAD */
    public static Response createResponseBad(String message) {
        return new Response(ResponseStatus.BAD, message);
    }

    /** Constructor with responseType defaulted to EXIT */
    public static Response createResponseExit() {
        return new Response(ResponseStatus.EXIT, "");
    }

    /** Gets response status. */
    public ResponseStatus getStatus() {
        return status;
    }

    /** Gets response message. */
    public String getMessage() {
        return message;
    }
}
