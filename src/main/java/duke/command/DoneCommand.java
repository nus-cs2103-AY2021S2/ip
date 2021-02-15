package duke.command;

import duke.TaskList;
import duke.Ui;
/**
 * Command to mark a given task as done.
 */
public class DoneCommand implements ICommand {
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructor for DoneCommand
     * @param ui Ui to display after execution
     * @param tasks TaskList object responsible for task management
     */
    public DoneCommand(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Marks the given task in parameters as done.
     * Prints out the error to ui if caught.
     */
    @Override
    public void execute(String parameters) {
        try {
            Integer count = Integer.parseInt(parameters);
            tasks.setTaskDone(count);
            String string = getDoneString(count);
            ui.createDukeDialog(string);
        } catch (NumberFormatException e) {
            ui.handleError(e);
        } catch (IllegalArgumentException e) {
            ui.handleError(e);
        }
    }

    private String getDoneString(Integer count) {
        String string = "";
        string += "Nice! I've marked this task as done:\n";
        string += tasks.getTask(count);
        return string;
    }
}
