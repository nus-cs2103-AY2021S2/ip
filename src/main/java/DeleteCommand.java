public class DeleteCommand extends Command {
    private String fullCommand;
    private int delTaskNum;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.delTaskNum = Integer.parseInt(fullCommand.replaceAll("[^0-9]", ""));
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException.TaskOutOfBoundsError, DukeException.FileLoadError {
        //check whether within range
        int numOfTasks = taskList.getTasksCount();
        if (this.delTaskNum > 0 && this.delTaskNum <= numOfTasks) {

            ui.taskDelMsg(taskList, delTaskNum);
            taskList.delTask(delTaskNum - 1);
            storage.save(taskList);
            /*
            System.out.println("Noted. I've removed this task:\n"
                    + taskList.getTasksList().get(delTaskNum - 1).toString() + "\n"
                    + "Now you have " + (numOfTasks - 1) + " tasks in the list.");
            taskList.delTask(delTaskNum - 1);
             */
        } else {
            throw new DukeException.TaskOutOfBoundsError("Task number out of bounds");
        }

    }
    public Boolean isExit() {
        return false;
    }
}
