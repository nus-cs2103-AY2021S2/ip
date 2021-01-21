public class DeleteCommand implements Command {
    int taskIndexToDone;
    Task taskToDone;
    TaskList taskList;

    public DeleteCommand(int taskIndexToDone) {
        this.taskIndexToDone = taskIndexToDone;
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
                if (i == taskIndexToDone) {
                    this.taskToDone = this.taskList.getNthTask(i);
                }
            }
            this.taskToDone.completeTask();
            return this.taskList;
        } catch (NullPointerException e) {
            throw new MikeInvalidInputException("OOPS!! The task is not in the list");
        }

    }

    @Override
    public String getResponse() {
        return String.format("____________________________________________________________\n" +
                        " Noted. I've removed this task:  \n  " +
                        "%s\n" +
                        " Now you have %d tasks in the list." +
                        "____________________________________________________________\n",
                        this.taskToDone.toString(), this.taskList.getNumTasks());
    }
}
