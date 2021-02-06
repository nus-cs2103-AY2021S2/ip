public class DeleteCommand extends Command {
    private int delTaskNum;

    public DeleteCommand(String fullCommand) {
        this.delTaskNum = Integer.parseInt(
                fullCommand.replaceAll("[^0-9]", ""));
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException.TaskOutOfBoundsError, DukeException.FileLoadError {
                //check whether within range
                int numOfTasks = taskList.getTasksCount();
                if (this.delTaskNum > 0 && this.delTaskNum <= numOfTasks) {

                    ui.taskDelMsg(taskList, delTaskNum);
                    taskList.delTask(delTaskNum - 1);
                    storage.save(taskList);
                } else {
                    throw new DukeException.TaskOutOfBoundsError("Task number out of bounds");
                }

    }
    public Boolean isExit() {
        return false;
    }
}
