import java.util.ArrayList;
import java.util.List;

public class Bot {

    private String name;
    private String logo;

    private boolean active;
    private List<String> memory;

    public Bot(String name) {
        this.name = name;
        this.logo = "  ___ _            _     ___      _   \n" + " / __(_)_ __  _ __| |___| _ ) ___| |_ \n"
                + " \\__ \\ | '  \\| '_ \\ / -_) _ \\/ _ \\  _|\n" + " |___/_|_|_|_| .__/_\\___|___/\\___/\\__|\n"
                + "             |_|                      \n\n";
        this.active = true;
        this.memory = new ArrayList<>();
    }

    private String formatMessage(String message) {
        return name + ": " + message.replace("{{bot:name}}", name);
    }

    public String getInitMessage() {
        return logo + formatMessage("Hi, my name is {{bot:name}}!");
    }

    public boolean isActive() {
        return active;
    }

    public String respond(String input) {
        // Clear leading and trailing whitespace
        input = input.strip();

        if (input.equalsIgnoreCase("hello")) {
            return formatMessage("Hello! My name is {{bot:name}}!");
        }
        if (input.equalsIgnoreCase("bye")) {
            active = false;
            return formatMessage("Bye. Hope to see you again soon!");
        }

        if (input.equalsIgnoreCase("list")) {
            String message = formatMessage("Printing list!");
            for (int i = 0; i < memory.size(); i++) {
                message += "\n" + memory.get(i);
            }
            return message;
        }

        memory.add(input);
        return formatMessage("I've added '" + input + "'.");
    }

}