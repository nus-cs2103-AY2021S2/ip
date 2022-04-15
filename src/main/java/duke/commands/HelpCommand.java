package duke.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import duke.tasks.TaskList;
import duke.utils.Storage;

/**
 * Represents a help command which displays information about the commands.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    private static final String UNKNOWN_COMMAND = "unknown";
    private static final Map<String, String> COMMAND_AND_DESCRIPTION = Map.of(
            "todo", "todo DESCRIPTION",
            "deadline", "deadline DESCRIPTION /by DATE",
            "event", "event DESCRIPTION /at DATE",
            "find", "find PHRASE",
            "done", "done INDEX",
            "delete", "delete INDEX",
            "list", "list",
            "bye", "bye",
            "help", "help (COMMAND)",
            "unknown", "This is not a valid command. Enter 'help' to see the list of our commands!"
    );
    private static final List<String> ALL_COMMANDS = new ArrayList<>(COMMAND_AND_DESCRIPTION.keySet());

    private String command;

    /**
     * Creates a HelpCommand object to display instructions of all the commands available in this chatbot.
     *
     * @param taskList the current list of Tasks.
     * @param storage the object in charge of writing to the local storage file.
     */
    public HelpCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
        this.command = null;
    }

    /**
     * Creates a HelpCommand object to display instructions of the specified commands available in this chatbot.
     *
     * @param taskList the current list of Tasks.
     * @param storage the object in charge of writing to the local storage file.
     * @param command the command to display instructions, if any.
     */
    public HelpCommand(TaskList taskList, Storage storage, String command) {
        super(taskList, storage);
        this.command = command;
    }

    @Override
    public String execute() {
        if (this.command != null) {
            return returnOneInstruction();
        }

        return returnAllInstructions();
    }

    private String returnOneInstruction() {
        if (COMMAND_AND_DESCRIPTION.get(this.command) == null) {
            return COMMAND_AND_DESCRIPTION.get(UNKNOWN_COMMAND);
        }

        StringBuilder stringBuilder = new StringBuilder("Here is the format for ")
                                            .append(this.command)
                                            .append(":");

        stringBuilder.append("\n\nCommand: ")
                .append(this.command)
                .append(", Input format: ")
                .append(COMMAND_AND_DESCRIPTION.get(this.command));

        if (this.command.equals(DeadlineCommand.COMMAND_WORD) || this.command.equals(EventCommand.COMMAND_WORD)) {
            stringBuilder.append("\n\nAcceptable date formats: d/M/yyyy HHmm, d MMM yy HHmm, dd-MM-yy HHmm");
        }

        return stringBuilder.toString();
    }

    private String returnAllInstructions() {
        StringBuilder stringBuilder = new StringBuilder("Here are the list of valid commands:");

        for (String command : ALL_COMMANDS) {
            if (command.equals("unknown")) {
                continue;
            }

            stringBuilder.append("\n\nCommand: ")
                    .append(command)
                    .append(", Format: ")
                    .append(COMMAND_AND_DESCRIPTION.get(command));
        }

        stringBuilder.append("\n\nAcceptable date formats: d/M/yyyy HHmm, d MMM yy HHmm, dd-MM-yy HHmm");

        return stringBuilder.toString();
    }
}
