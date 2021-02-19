/**
 * Represents the Command for "clear" for the Henchman object. CommandClear helps to clear all tasks in TaskList.
 */
public class CommandClear extends Command {
    /**
     * Removes all tasks in tasks and saves the new empty list via storage
     *
     * @param tasks TaskList of in which all tasks are cleared.
     * @param storage Storage of Henchman object.
     * @return Return message upon successful clearing of tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.clearAllTasks();
        storage.save(tasks);

        assert tasks.getTasks().isEmpty() : "task list not cleared!";
        return toHenchmanOutput();
    }

    /**
     * Returns the string representation of the message to be printed.
     *
     * @return String representation of the message to be printed.
     */
    @Override
    public String toHenchmanOutput() {
        return "Clear complete, the task list is now empty.";
    }
}
