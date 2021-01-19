import java.util.List;
import java.util.ArrayList;

/**
 * Owen is a personal assistant chatbot.
 * 
 * As Owen is an immutable class, modifications will return a copy
 * with updated internal state.
 */
public class Owen implements Chatbot {
    private final boolean isRunning;
    private final Response latestResponse;
    private final List<String> items;

    /**
     * Creates an Owen chatbot with a hello response.
     */
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

        this.items = new ArrayList<>();
    }

    private Owen(boolean isRunning, Response latestResponse, List<String> items) {
        this.isRunning = isRunning;
        this.latestResponse = latestResponse;
        this.items = items;
    }

    @Override
    public Owen shutdown() {
        Response shutdownResponse = new DefaultResponse("Bye. Hope to see you again soon!");
        return new Owen(false, shutdownResponse, this.items);
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
        case "list":
            Response listResponse = new DefaultResponse(this.getItemsListAsString());
            return new Owen(this.isRunning, listResponse, this.items);
        case "bye":
            return this.shutdown();
        default:
            List<String> itemsCopy = new ArrayList<>(this.items);
            itemsCopy.add(command);
            String addedFormat = "added: %s";
            Response addResponse = new DefaultResponse(String.format(addedFormat, command));
            return new Owen(this.isRunning, addResponse, itemsCopy);
        }
    }

    private String getItemsListAsString() {
        String[] listItemStrings = new String[this.items.size()];
        String listItemFormat = "%d. %s";
        for (int i = 0; i < this.items.size(); i++) {
            listItemStrings[i] = String.format(listItemFormat, i + 1, this.items.get(i));
        }
        return String.join("\n", listItemStrings);
    }
}
