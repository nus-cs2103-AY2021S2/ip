/**
 * Interface for a chatbot.
 */
public interface Chatbot {
    /**
     * Shutsdown the chatbot and sets a goodbye response.
     * @return Chatbot that has been shut down.
     */
    public Chatbot shutdown();

    /**
     * Checks if the chatbot is currently running.
     * @return Boolean indicating if chatbot is running.
     */
    public boolean isRunning();

    /**
     * Gets latest response from the chatbot.
     * @return Latest formatted response from the chatbot.
     */
    public Response getResponse();

    /**
     * Parses command and returns chatbot with updated state.
     * @param command String command to parse.
     * @return Chatbot with updated internal state.
     */
    public Chatbot parseCommand(String command);
}
