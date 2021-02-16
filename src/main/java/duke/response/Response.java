package duke.response;

import duke.ui.Ui;

/**
 * Response class that encapsulates a message to be displayed to the user as well as the type of status of the command
 */
public class Response {
    private String text;
    private ResponseStatus status;

    /**
     * Constructor for Response objects
     * @param text Text to be displayed to user
     * @param status Type of response (OK, EXIT, ERROR)
     */
    public Response(String text, ResponseStatus status) {
        this.text = text;
        this.status = status;
    }

    public String getText() {
        return Ui.displayMessage(text);
    }

    public ResponseStatus getStatus() {
        return status;
    }
}
