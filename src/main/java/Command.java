import java.util.HashMap;
import java.util.Map;

/**
 * Command is an Enum class that handles valid commands to the DukeBot chat-bot.
 *
 * The possible valid commands are:
 *   LIST
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public enum Command {
    LIST("list"),           // To list all Tasks
    TODO("todo"),           // For todo tasks
    EVENT("event"),         // For /at events
    DEADLINE("deadline"),   // For /by tasks
    DONE("done"),           // For marking a Task as complete
    END("bye");             // For terminating the DukeBot


    private final String command;

    Command(String command) {
        this.command = command;
    }

    /**
     * Returns the command string relevant to the given Enum.
     *
     * @return String representing the command for the given Enum
     */
    public String getCommand() {
        return String.valueOf(this.command);
    }

    //@@author Lokesh Gupta
    //Reused from https://howtodoinjava.com/java/enum/java-enum-string-example/
    private static final Map<String, Command> lookup = new HashMap<>();

    // Populate the lookup table with all possible Command Enums
    static {
        for (Command cmd : Command.values()) {
            lookup.put(cmd.getCommand(), cmd);
        }
    }

    /**
     * Searches for the relevant Enum that matches the String command.
     *
     * @param command The String command whose Enum is to be returned
     * @return Command Enum that matches the given String command or null
     */
    public static Command get(String command) {
        return lookup.get(command);
    }
    //@@author

    /**
     * Overloads Object's toString method to return the command string instead.
     *
     * @return The command string
     */
    @Override
    public String toString() {
        return this.getCommand();
    }
}
