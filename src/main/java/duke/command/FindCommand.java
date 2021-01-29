package duke.command;

import duke.exception.InvalidDescriptionException;
import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

/**
 * The FindCommand class denotes a find command to the Duke chat bot.
 */
public class FindCommand extends Command {
    /**
     * Constructs a FindCommand
     * @param taskDescription    The description of the task.
     */
    public FindCommand(String taskDescription) {
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
            taskList.find(taskDescription, ui);
        } catch (InvalidDescriptionException ex) {
            System.out.println("     " + ex.getMessage());
        }

    }

    /**
     * Do not exit the Duke program
     * @return   A signal that indicate a continuation of the Duke program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
