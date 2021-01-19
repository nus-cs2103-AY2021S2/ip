public interface Chatbot {
    public Chatbot shutdown();
    public boolean isRunning();
    public Response getResponse();
    public Chatbot parseCommand(String command);
}
