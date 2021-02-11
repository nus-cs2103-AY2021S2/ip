package duke;

public class DukeResponse {
    private String response;
    private boolean isExit;

    /**
     * Creates a new DukeResponse instance with the specified response.
     *
     * @param response
     */
    public DukeResponse(String response) {
        this.response = response;
        this.isExit = false;
    }

    /**
     * Creates a new DukeResponse instance with the specified response and isExit flag.
     *
     * @param response
     */
    public DukeResponse(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    /**
     * Gets the response.
     *
     * @return response
     */
    public String getResponse() {
        return response;
    }

    /**
     * Returns whether the response is exit.
     *
     * @return isExit
     */
    public boolean getIsExit() {
        return isExit;
    }
}