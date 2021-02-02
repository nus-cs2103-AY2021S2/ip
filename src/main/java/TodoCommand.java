public class TodoCommand extends AddTaskCommand {
    public TodoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws  DukeException {
        tasks.add(new Todo(arguments));
        super.execute(tasks, ui, storage);
    }
}
