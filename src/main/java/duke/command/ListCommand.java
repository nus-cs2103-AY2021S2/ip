package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {

    }
    /**
     * Executes the list command.
     * Displays all the tasks in the task list.
     *
     * @param tasks a list of tasks.
     * @param storage the storage of the Duke object.
     *
     * @return the output to be displayed to user.
     */
    public String execute(TaskList tasks, Storage storage) {
        String output = "";
        output += "Here are the tasks in your list:\n";
        int index = 1;
        for (Task task : tasks.getTasks()) {
            output += String.format("%d. %s", index, task.toString());
            output += Ui.showNewLine();
            index++;
        }
        return output;
    }
}
