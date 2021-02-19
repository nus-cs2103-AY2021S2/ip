/**
 * Represents the Command for "delete" for the Henchman object. CommandDelete helps to deletes the task specified by
 * the index from the TaskList.
 */
public class CommandDelete extends Command {
    private final int index;

    public CommandDelete (int index) {
        this.index = index;
    }

    /**
     * Delete task specified by index from tasks, and saves the updated tasks.
     *
     * @param tasks TaskList of Henchman object.
     * @param storage Storage of Henchman object.
     * @return Message to return upon deletion of task specified by index.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.deleteTask(index);
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
        return "Alrighty bossman. I shall wipe this task off the face of the earth: ";
    }
}
