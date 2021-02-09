package duke.command;



import duke.task.TaskList;
import duke.ui.ErrorBox;
import duke.ui.Ui;


/**
 * Sub-class of command that only represents and executes a delete instruction of a user.
 */

public class DeleteCommand extends Command {
    private static final String NO_DATE = "";


    /**
     * Create a DeleteCommand object with given task and date.
     * @param task index of the task to be deleted.
     */
    public DeleteCommand(String task) {
        super("delete", task, NO_DATE, false,
            command -> handleDelete(task));
    }


    /**
     * handle delete command key in by user by removing the task from the list if there is any.
     *
     * @param task user task in String.
     */
    private static String handleDelete(String task) {
        String response = "";
        try {
            int num = Integer.parseInt(task);
            response = TaskList.deleteTask(num);
        } catch (NumberFormatException e) {
            ErrorBox.display(Ui.KEY_IN_NUMBER);
        }
        return response;
    }
}
