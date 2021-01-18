import java.util.HashMap;
import java.util.Map;

public enum Commands {
    TODO("todo"), DEADLINE("deadline"),
    EVENT("event"), DONE("done"),
    LIST("list"), DELETE("delete"),
    HELP("help"), BYE("bye"),
    INVALID_COMMAND(null);

    private String commandString;

    private Commands (String commandString) {
        this.commandString = commandString;
    }

    private static final Map<String, Commands> lookup = new HashMap<String, Commands>();

    static {
        for (Commands command: Commands.values()) {
            lookup.put(command.getCommandStr(), command);
        }
    }

    public String getCommandStr() {
        return this.commandString;
    }

    public static Commands get(String str) {
        Commands command = lookup.get(str);
        return command == null ? INVALID_COMMAND : command;
    }
}
