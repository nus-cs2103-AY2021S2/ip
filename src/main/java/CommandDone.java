/**
 * Represents the Command for "done" for the Henchman object. CommandDone helps to mark the task specified by
 * the index as done.
 */
public class CommandDone extends Command {
    private final int index;

    public CommandDone(int index) {
        this.index = index;
    }

    /**
     * Marks task specified by index as done, and saves the updated tasks.
     *
     * @param tasks TaskList of Henchman object.
     * @param storage Storage of Henchman object.
     * @return Message to return upon marking of task specified by index as done.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.doneTask(index);
            storage.save(tasks);
            return this.toHenchmanOutput() + "\n" + task.toString();
        } catch (IndexOutOfBoundsException e) {
            return "Index provided is out of bounds! Please provide a valid index (1 to "
                    + tasks.getTasks().size() + ").";
        }
    }

    /**
     * Returns the string representation of the message to be printed.
     *
     * @return String representation of the message to be printed.
     */
    @Override
    public String toHenchmanOutput() {
        return "Impressive, yet another task has been done: ";
    }
}
