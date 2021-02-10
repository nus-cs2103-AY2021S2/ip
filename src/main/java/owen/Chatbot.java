package owen;

/**
 * Interface for a chatbot.
 */
public interface Chatbot {
    /**
     * Shutsdown the chatbot and sets a goodbye response.
     *
     * @return Chatbot that has been shut down.
     */
    public Chatbot shutdown();

    /**
     * Checks if the chatbot is currently running.
     *
     * @return Boolean indicating if chatbot is running.
     */
    public boolean isRunning();

    /**
     * Gets latest response from the chatbot.
     *
     * @return Latest response from the chatbot.
     */
    public String getResponse();

    /**
     * Parses command and returns chatbot with updated state.
     *
     * @param commandString String command to parse.
     * @return Chatbot with updated internal state.
     */
    public Chatbot parseCommand(String commandString);
}
