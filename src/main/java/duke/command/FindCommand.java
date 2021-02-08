package duke.command;

import java.util.ArrayList;

import duke.exception.InvalidDescriptionException;
import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;
import duke.model.Task;

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
     * @param taskList The list of recorded tasks.
     * @param ui       The user interface.
     * @param storage  The list of recorded user inputs data.
     * @return         The message replied by Duke chat bot.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ArrayList<Task> matchingTasks = taskList.find(taskDescription);
            return ui.findCommandInteraction(matchingTasks);
        } catch (InvalidDescriptionException ex) {
            return "     " + ex.getMessage();
        }
    }
}
