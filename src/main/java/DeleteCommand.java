public class DeleteCommand extends Command {

    public DeleteCommand(String command, String description) {
        this.command = command;
        this.description = description;
        this.date = "";
    }

    private void deleteProcess(String selection, TaskList tasks) {
        int taskNum = Integer.parseInt(selection);
        Task task = tasks.get(taskNum);
        tasks.remove(task);
        output = "Noted. I've removed this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private String getRemainingTasks(TaskList tasks) {
        return "\n\tNow you have " + tasks.size() + " tasks in the list.";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Integer.parseInt(description) > tasks.size() || Integer.parseInt(description) <= 0) {
            // Selection out of taskList range
            throw new DukeException(command, DukeExceptionType.SELECTION_EXCEED_RANGE);
        }
        deleteProcess(description, tasks);
        storage.save(tasks);
        ui.response(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
