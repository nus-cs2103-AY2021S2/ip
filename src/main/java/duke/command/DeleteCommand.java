package duke.command;

import duke.TaskList;
import duke.task.Task;

/**
 * Command to delete tasks from the taskList when executed.
 */
public class DeleteCommand implements ICommand {
    private TaskList tasks;

    /**
     * Constructor method for DeleteCommand.
     *
     * @param tasks TaskList object that the command will delete from when executed
     */
    public DeleteCommand(TaskList tasks) {
        this.tasks = tasks;
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

            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask.toString());
            System.out.println(String.format("Now you have %d tasks in the list", tasks.getTasks().size()));
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid argument for delete");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
