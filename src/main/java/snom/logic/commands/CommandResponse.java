package snom.logic.commands;

/**
 * Represents the response after {@code Command} being executed.
 */
public class CommandResponse {
    private String responseMsg;
    private boolean isExit;

    /**
     * Constructs a {@code CommandResponse}
     *
     * @param responseMsg   response message
     * @param isExit        indicator to exit the app
     */
    public CommandResponse(String responseMsg, boolean isExit) {
        this.responseMsg = responseMsg;
        this.isExit = isExit;
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
}
