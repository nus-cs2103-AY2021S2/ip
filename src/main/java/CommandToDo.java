/**
 * Represents the Command for "todo" for the Henchman object. CommandToDo helps to initiate creation and adding
 * of the ToDo Task with the specified attributes into the provided TaskList.
 */
public class CommandToDo extends Command {
    private final String DESCRIPTION;

    public CommandToDo(String description) {
        this.DESCRIPTION = description;
    }

    /**
     * Creates an ToDo Task with the specified description and adds it into the provided TaskList. Then, save the
     * updated TaskList.
     *
     * @param tasks TaskList of Henchman object, contains all the Tasks that were added.
     * @param storage Storage of Henchman object, saves the TaskList into a text file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ToDo toDo = new ToDo(DESCRIPTION);
        tasks.addTask(toDo);
        storage.save(tasks);
        return this.toHenchmanOutput() + "\n" + toDo.toString();
    }

    /**
     * Returns the string representation of the message to be printed.
     *
     * @return String representation of the message to be printed.
     */
    @Override
    public String toHenchmanOutput() {
        return "Roger that boss, I've added a new ToDo: ";
    }
}
