public class Owen implements Chatbot {
    private final boolean isRunning;
    private final Response latestResponse;

    public Owen() {
        this.isRunning = true;

        StringBuilder stringBuilder = new StringBuilder();
        String logo = ""
                + " /\\_/\\ \n"
                + "((OvO))\n"
                + "():::()\n"
                + " VV-VV \n";
        stringBuilder.append(logo);
        stringBuilder.append("\nHello I am Owen the Owl!");
        this.latestResponse = new DefaultResponse(stringBuilder.toString());
    }

    private Owen(boolean isRunning, Response latestResponse) {
        this.isRunning = isRunning;
        this.latestResponse = latestResponse;
    }

    @Override
    public Owen shutdown() {
        Response shutdownResponse = new DefaultResponse("Bye. Hope to see you again soon!");
        return new Owen(false, shutdownResponse);
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public Response getResponse() {
        return this.latestResponse;
    }

    @Override
    public Owen parseCommand(String command) {
        switch (command) {
        case "bye":
            return this.shutdown();
        default:
            Response response = new DefaultResponse(command);
            return new Owen(true, response);
        }
    }
}
