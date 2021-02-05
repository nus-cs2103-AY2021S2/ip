public class DoneCommand extends Command {
    private String fullCommand;
    private int doneTaskNum;

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.doneTaskNum = Integer.parseInt(fullCommand.replaceAll("[^0-9]", ""));
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException.TaskOutOfBoundsError, DukeException.FileLoadError {
        //check whether within range
        int numOfTasks = taskList.getTasksCount();
        if (this.doneTaskNum > 0 && this.doneTaskNum <= numOfTasks) {
            taskList.getTasksList().get(doneTaskNum - 1).markAsDone();
            ui.taskDoneMsg(taskList, doneTaskNum);
            storage.save(taskList);
            /*
            System.out.println("Nice! I've marked this task as done:\n"
                    + taskList.getTasksList().get(doneTaskNum - 1).toString());

             */
        } else {
            throw new DukeException.TaskOutOfBoundsError("Task number out of bounds");
        }

    }
    public Boolean isExit() {
        return false;
    }
}
