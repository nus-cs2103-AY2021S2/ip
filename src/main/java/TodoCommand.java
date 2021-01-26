public class TodoCommand extends Command {
    private Todo todo;

    public TodoCommand(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("Argument not specified!");
        }
        this.todo = new Todo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        tasks.addTask(todo);
        ui.printAddTaskAck(todo, tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
