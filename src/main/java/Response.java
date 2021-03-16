public class Response {
    /**
     *boolean of isExit to create a response.
     */
    private boolean isExit;
    /**
     * String of response.
     */
    private final String responseString;

    /**
     * Constructor of response.
     * @param responseString
     * @param isExit
     */
    public Response(String responseString, boolean isExit) {
        this.responseString = responseString;
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    public String getResponseString() {
        return responseString;
    }
}
