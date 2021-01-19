public class Owen implements Chatbot {
    private final Response latestResponse;

    public Owen() {
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

    @Override
    public Response getResponse() {
        return this.latestResponse;
    }
}
