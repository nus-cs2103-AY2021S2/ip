/**
 * Represents a command involving the addition of a task to be done.
 */
public class ToDoCommand extends Command {
    private String todo;

    /**
     * Constructor for ToDoCommand.
     * @param todo Name of task.
     */
    ToDoCommand(String todo) {
        this.todo = todo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(this.todo);
        tasks.add(task);
        ui.addtask(task.toString(), tasks.size());
        storage.savetasks(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isExit() {
        return false;
    }

    /**
     * Checks the equivalence of ToDoCommand this and Object obj.
     * If obj is an instance of the ToDoCommand class and all attributes are equivalent, it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ToDoCommand) {
            ToDoCommand tdc = (ToDoCommand) obj;
            return tdc.todo.equals(this.todo);
        }
        return false;
    }
}
