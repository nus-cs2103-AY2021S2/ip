package duke;

public class DukeResponse {
    private String message;
    private boolean isExit;

    /**
     * Creates a DukeResponse with the given message and default isExit of 'false'
     *
     * @param message
     */
    public DukeResponse(String message) {
        this.message = message;
        this.isExit = false;
    }

    /**
     * returns the response message
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns a boolean stating if the user wants to exit
     *
     * @return isExit flag
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets the isExit flag to indicate if the user wants to exit
     *
     * @param isExit
     */
    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }
}
