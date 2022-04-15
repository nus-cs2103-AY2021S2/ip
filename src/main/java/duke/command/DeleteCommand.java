package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to delete tasks from the taskList when executed.
 */
public class DeleteCommand implements ICommand {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor method for DeleteCommand.
     *
     * @param ui Ui to display
     * @param tasks TaskList object that the command will delete from when executed
     */
    public DeleteCommand(Ui ui, TaskList tasks) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Will attempt to delete task at given parameters. Will print out an error if an invalid
     * input was given.
     *
     * @param parameters index of task to be deleted
     */
    @Override
    public void execute(String parameters) {
        try {
            int count = Integer.parseInt(parameters.strip());
            Task removedTask = tasks.deleteTask(count);
            String string = getDeletedString(removedTask);
            ui.createDukeDialog(string);
        } catch (NumberFormatException e) {
            ui.handleError("Error: Invalid argument for delete");
        } catch (IllegalArgumentException e) {
            ui.handleError(e);
        }
    }

    private String getDeletedString(Task removedTask) {
        String string = "";
        string += "Noted. I've removed this task:\n";
        string += removedTask.toString() + "\n";
        string += String.format("Now you have %d task(s) in the list", tasks.getTasks().size()) + "\n";
        return string;
    }

}
