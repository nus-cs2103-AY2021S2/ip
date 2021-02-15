package snom.logic.commands;

/**
 * Represents the response after {@code Command} being executed.
 */
public class CommandResponse {
    private String responseMsg;
    private boolean isExit;
    private boolean isError;

    /**
     * Constructs a {@code CommandResponse} with default error set to false
     *
     * @param responseMsg   response message
     * @param isExit        indicator to exit the app
     */
    public CommandResponse(String responseMsg, boolean isExit) {
        this.responseMsg = responseMsg;
        this.isExit = isExit;
        this.isError = false;
    }

    /**
     * Constructs a {@code CommandResponse}
     *
     * @param responseMsg   response message
     * @param isExit        indicator to exit the app
     * @param isError       indicator for error response
     */
    public CommandResponse(String responseMsg, boolean isExit, boolean isError) {
        this.responseMsg = responseMsg;
        this.isExit = isExit;
        this.isError = isError;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public boolean isError() {
        return isError;
    }
}
