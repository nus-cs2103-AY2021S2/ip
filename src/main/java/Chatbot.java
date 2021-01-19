public interface Chatbot {
    public Chatbot shutdown();
    public boolean isRunning();
    public Response getResponse();
}
