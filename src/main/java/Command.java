import java.util.Map;
import java.util.HashMap;

/**
 * Command is an Enum class that handles valid commands to the DukeBot chat-bot.
 * <p>
 * The possible valid commands are:
 * LIST
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public enum Command {
    LIST("list"),           // For listing all Tasks
    TODO("todo"),           // For Todo Tasks
    EVENT("event"),         // For /at Events
    DEADLINE("deadline"),   // For /by Deadlines
    DONE("done"),           // For marking a Task as complete
    DELETE("delete"),       // For deleting a Task
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
     * @param commandStr The String command whose Enum is to be returned
     * @return Command Enum that matches the given String command or null
     */
    public static Command get(String commandStr) throws DukeException {
        Command command = lookup.get(commandStr);
        if (command == null) throw new DukeException("Not a valid command string");
        return command;
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
