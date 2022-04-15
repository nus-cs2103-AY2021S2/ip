package owen;

/**
 * A default formatted chatbot response.
 */
public class DefaultResponse implements Response {
    private final String response;

    /**
     * Creates a default formatted chatbot response from a String.
     *
     * @param response Raw unformatted response string.
     */
    public DefaultResponse(String response) {
        this.response = response;
    }

    @Override
    public String getUnformattedResponse() {
        return this.response;
    }

    @Override
    public String getFormattedResponse() {
        String responseFormat =
                "____________________________________________________________\n"
                + "%s\n"
                + "____________________________________________________________\n";

        String formattedResponse = String.format(responseFormat, this.getUnformattedResponse());
        // Indent response by 4 spaces
        formattedResponse = formattedResponse.replaceAll("(?m)^", "    ");
        return formattedResponse;
    }

    @Override
    public String toString() {
        return this.getFormattedResponse();
    }
}
