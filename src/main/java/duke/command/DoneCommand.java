package duke.command;


import duke.task.TaskList;
import duke.ui.ErrorBox;
import duke.ui.Ui;


/**
 * Sub-class of Command that represents and execute the done instruction of user.
 */
public class DoneCommand extends Command {
    private static final String NO_DATE = "";

    /**
     * Create a DoneCommand object with the given task.
     * @param task user task.
     */
    public DoneCommand(String task) {
        super("done", task, NO_DATE, false, command -> handleDone(task));
    }


    /**
     * handle done command by marking the task as done.
     *
     * @param task name of the user task.
     */
    private static String handleDone(String task) {
        String response = "";
        try {
            int num = Integer.parseInt(task);
            response = TaskList.markDone(num);
        } catch (NumberFormatException e) {
            ErrorBox.display(Ui.KEY_IN_NUMBER);
        }

        return response;
    }


}
