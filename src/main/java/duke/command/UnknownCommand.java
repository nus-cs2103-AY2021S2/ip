package duke.command;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;
import duke.exception.InvalidInputException;

/**
 * The UnknownCommand class denotes an unknown command to the Duke chat bot.
 */
public class UnknownCommand extends Command {
    /**
     * Constructs an UnknownCommand.
     * @param taskDescription   The description of the task.
     */
    public UnknownCommand(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Executing the command
     * @param taskList A list of recorded tasks.
     * @param ui       A user interface.
     * @param storage  A list of recorded user inputs data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.unknownCommandInteraction();
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Do not exit the Duke program
     * @return   A signal that indicate a continuation of the Duke program.
     */
    public boolean isExit() {
        return false;
    }
}
