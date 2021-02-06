package duke.command;

import duke.TaskList;
import duke.task.Task;
import duke.Ui;

/**
 * Command that prints the list existing in duke.TaskList.
 */
public class PrintListCommand implements ICommand {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor Method for PrintListCommand
     * @param tasks TaskList object that contains the information of all tasks
     */
    public PrintListCommand(Ui ui, TaskList tasks) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Prints out the entire list of all the tasks that exists
     * in the application.
     */
    @Override
    public void execute(String parameters) {
        ui.createDukeDialog(listToString());
    }

    private String listToString() {
        String content = "";
        Integer count = 1;
        for (Task t: tasks.getTasks()) {
            content += count.toString() + ".";
            content += t.toString();
            content += "\n";
            count++;
        }
        return content.trim();
    }
}
