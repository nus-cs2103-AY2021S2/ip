package duke.command;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.ui.ErrorBox;
import duke.ui.Ui;


/**
 * Sub-class of Command that represents and execute the done instruction of user.
 */
public class DoneCommand extends Command {


    public DoneCommand(String task, String date) {
        super("done", task, date, false,
                command -> handleDone(task));
    }


    /**
     * handle done command by marking the task as done.
     *
     * @param task name of the user task.
     */
    private static String handleDone(String task) {
        if (task.length() > 0) {
            try {
                int num = Integer.parseInt(task);
                String result = TaskList.markDone(num);
                return result;
            } catch (NumberFormatException e) {
                DukeException.numberFormatException();
                ErrorBox.display(Ui.KEY_IN_NUMBER);
            }
        }
        return "";
    }


}
