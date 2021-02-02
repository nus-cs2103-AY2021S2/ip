public class AddTaskCommand extends Command{
    public AddTaskCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showNewLine("Got it I've added this task: ");
        ui.showNewLine(tasks.get(tasks.size() - 1).toString());
        ui.showNewLine(String.format("Now you have %d tasks in the list", tasks.size()));
        storage.saveTasksToFile(tasks);
    }
}
