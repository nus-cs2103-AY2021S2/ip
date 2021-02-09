package duke.commands;

import java.util.Map;

import duke.tasks.TaskList;
import duke.utils.Storage;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    private static final String UNKNOWN_COMMAND = "unknown";
    private static final Map<String, String> COMMAND_AND_DESCRIPTION = Map.of(
            "todo", "todo task description",
            "deadline", "deadline task description /by d MMM yy HHmm",
            "event", "event task description /at d MMM yy HHmm",
            "find", "find word",
            "done", "done index",
            "delete", "delete index",
            "list", "list",
            "bye", "bye",
            "help", "help",
            "unknown", "This is not a valid command. Enter 'help' to see the list of our commands!"
    );

    private final String command;

    /**
     * Creates a HelpCommand object to display instructions of the commands available in this chatbot.
     * If a non-null command is passed in, only instructions of that particular command will be returned.
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
        if (command != null) {
            return returnOneInstruction();
        }

        return returnAllInstructions();
    }

    private String returnOneInstruction() {
        if (COMMAND_AND_DESCRIPTION.get(command) == null) {
            return COMMAND_AND_DESCRIPTION.get(UNKNOWN_COMMAND);
        }

        StringBuilder stringBuilder = new StringBuilder("Here is the format for ")
                                            .append(command)
                                            .append(":");

        stringBuilder.append("\n\nCommand: ")
                .append(command)
                .append(", Input format: ")
                .append(COMMAND_AND_DESCRIPTION.get(command));

        return stringBuilder.toString();
    }

    private String returnAllInstructions() {
        StringBuilder stringBuilder = new StringBuilder("Here are the list of valid commands:");

        for (String command : COMMAND_AND_DESCRIPTION.keySet()) {
            stringBuilder.append("\n\nCommand: ")
                    .append(command)
                    .append(", Input format: ")
                    .append(COMMAND_AND_DESCRIPTION.get(command));
        }

        return stringBuilder.toString();
    }
}
