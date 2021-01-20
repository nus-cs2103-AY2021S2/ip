public class DoneCommand implements Command{

    int taskToDone;
    TaskList taskList;

    public DoneCommand(int taskToDone) {
        this.taskToDone = taskToDone;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;

        for (int i = 1; i <= this.taskList.getNumTasks(); i++) {
            if (i == taskToDone) {
                this.taskList.getNthTask(i).completeTask();
            }
        }
        return this.taskList;
    }

    @Override
    public String getResponse() {
        return this.taskList.toString();
    }
}
