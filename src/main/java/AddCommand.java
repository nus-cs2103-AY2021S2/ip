/**
 * class that handles the command to add ToDo, Events or Deadlines.
 */

public class AddCommand extends Command {

    private TaskList tasks;
    private Task task;

    public AddCommand(TaskList tasks, Task task) {
        this.tasks = tasks;
        this.task = task;
    }

    /**
     * Method that adds a task to the current list of tasks and returns
     * the result of task that has been added.
     */
    public Result execute() {
        String output = "";
        boolean hasDuplicate = tasks.checkForDuplicate(this.tasks.getDuplicateChecker(), task);
        if (!hasDuplicate) {
            tasks.getStorage().add(task);
            output += "ALRIGHT. I HAVE ALREADY ADDED THE TASK!!!\n"
                    + task.toString()
                    + "\n"
                    + "Now you have " + tasks.getStorage().size()
                    + " tasks in the list.";
        } else {
            output += "Item is a duplicate and has already been added.\n"
                    + "Please check again.";
        }
        assert output != "" : "output should not be empty.";
        Result result = new Result(output);
        return result;
    }
}
