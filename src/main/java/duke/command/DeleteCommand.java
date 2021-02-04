package duke.command;


import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.ui.ErrorBox;
import duke.ui.Ui;


/**
 * Sub-class of command that only represents and executes a delete instruction of a user.
 */

public class DeleteCommand extends Command {


    public DeleteCommand(String task, String date) {
        super("delete", task, date, false,
            command -> handleDelete(task, date));
    }


    /**
     * handle delete command key in by user by removing the task from the list if there is any.
     *
     * @param task user task in String.
     * @param date date of the task.
     */
    private static String handleDelete(String task, String date) {
        if (task.length() > 0 && date.equals("")) {
            try {
                int num = Integer.parseInt(task);
                String result = TaskList.deleteTask(num);
                return result;
            } catch (NumberFormatException e) {
                DukeException.numberFormatException();
                ErrorBox.display(Ui.KEY_IN_NUMBER);
            }
        }

        return "";
    }
}
