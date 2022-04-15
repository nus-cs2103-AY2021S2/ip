package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.task.Priority;
import duke.task.Task;

/**
 * Command to set indicated task's priority.
 */
public class SetPriorityCommand implements ICommand {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor method for SetPriorityCommand
     *
     * @param ui
     * @param tasks
     */
    public SetPriorityCommand(Ui ui, TaskList tasks) {
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
            //Parse all inputs into its proper types
            String[] inputArray = Parser.parseSetPriority(parameters);
            int count = Integer.parseInt(inputArray[0].trim());
            int priorityInt = Integer.parseInt(inputArray[1].trim());

            //Get task from TaskList and set its priority
            Priority priority = Priority.valueOf(priorityInt);
            Task selectedTask = tasks.getTask(count);
            selectedTask.setPriority(priority);

            String string = getPriorityString(selectedTask);
            ui.createDukeDialog(string);
        } catch (NumberFormatException e) {
            ui.createDukeDialog("Error: Invalid first argument for setting Priority");
        } catch (IllegalArgumentException e) {
            ui.handleError(e);
        }
    }

    private String getPriorityString(Task task) {
        String string = "";
        string += "Noted. I've changed this task priority:\n";
        string += task.toString() + "\n";
        return string;
    }
}
