package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;

/**
 * A class that interacts with TaskList to add and modify task
 */
public class Command {

    protected static String input;
    protected static boolean isExit;

    /** Create command object
     * @param input
     * @param taskList
     */
    public Command (String input, TaskList taskList) {
        this.input = input;
        this.isExit = false;
    }

    /** Allow each command to execute their own function
     * @param tasklist
     * @param ui
     * @param storage
     * @return
     */
    public TaskList execute(TaskList tasklist, UI ui, DataStorage storage) throws DukeException {
        if (this.input.equals("bye")) {
            Command ec = new ExitCommand();
            ec.execute(tasklist, ui , storage);
        }
        return tasklist;
    }

    /** Return boolean to terminate/continue the program
     * @return boolean
     */
    public static boolean isExit() {
        return isExit;
    }






}
