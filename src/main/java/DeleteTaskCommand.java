public class DeleteTaskCommand extends Command {
    private int taskIndex;

    public DeleteTaskCommand(String taskIndex) throws DukeException {
        if (taskIndex.isBlank()) {
            throw new DukeException("I'm not sure which task you want to delete!");
        }
        try {
            this.taskIndex = Integer.parseInt(taskIndex.trim());
        } catch (NumberFormatException e) {
            throw new DukeException("I can only find a task with its index number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTask(taskIndex - 1);
            tasks.deleteTask(taskIndex - 1);
            ui.printDeleteTaskMessage(task, tasks);
            storage.saveTasksToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I cannot find the task you are referring to.");
        }
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}