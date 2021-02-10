package owen;

/**
 * Interface for a formatted chatbot response.
 */
public interface Response {
    /**
     * Returns plain unformatted response as a String.
     *
     * @return Unformatted response string.
     */
    public String getUnformattedResponse();

    /**
     * Returns formatted response as a String.
     *
     * @return Formatted response string.
     */
    public String getFormattedResponse();
}
