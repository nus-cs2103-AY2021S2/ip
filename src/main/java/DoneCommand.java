public class DoneCommand extends Command {

    public DoneCommand(String command, String description) {
        this.command = command;
        this.description = description;
        this.date = "";
    }

    private void doneProcess(String selection, TaskList tasks) {
        int taskNum = Integer.parseInt(selection);
        Task task = tasks.get(taskNum);
        task.markAsDone();
        output = "Nice! I've marked this task as done:\n\t  "
                + task.toString();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Integer.parseInt(description) > tasks.size() || Integer.parseInt(description) <= 0) {
            // Selection out of taskList range
            throw new DukeException(command, DukeExceptionType.SELECTION_EXCEED_RANGE);
        }
        doneProcess(description, tasks);
        storage.save(tasks);
        ui.response(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
