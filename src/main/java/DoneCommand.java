public class DoneCommand extends Command {
    public DoneCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(this.arguments) - 1;
        Task doneTask = tasks.get(index);
        doneTask.setDone(true);
        ui.showNewLine("Nice! I've marked this task as done:");
        ui.showNewLine(doneTask.toString());
        storage.saveTasksToFile(tasks);
    }
}
