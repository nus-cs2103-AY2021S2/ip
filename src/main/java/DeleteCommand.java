public class DeleteCommand implements Command {
    int taskIndexToDelete;
    Task taskToDelete;
    TaskList taskList;

    public DeleteCommand(int taskIndexToDelete) {
        this.taskIndexToDelete = taskIndexToDelete;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public TaskList runCommand(TaskList taskList) throws MikeInvalidInputException {
        this.taskList = taskList;
        try {
            for (int i = 1; i <= this.taskList.getNumTasks(); i++) {
                if (i == taskIndexToDelete) {
                    this.taskToDelete = this.taskList.getNthTask(i);
                }
            }
            this.taskList.deleteNthTask(taskIndexToDelete);
            return this.taskList;
        } catch (NullPointerException e) {
            throw new MikeInvalidInputException("OOPS!! The task is not in the list");
        }

    }

    @Override
    public String getResponse() {
        return String.format(
                        "Noted. I've removed this task:  \n  " +
                        "%s\n" +
                        "Now you have %d tasks in the list.\n",
                        this.taskToDelete.toString(), this.taskList.getNumTasks());
    }
}
