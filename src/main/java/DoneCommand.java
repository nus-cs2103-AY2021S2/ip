public class DoneCommand implements Command {

    int taskIndexToDone;
    Task taskToDone;
    TaskList taskList;

    public DoneCommand(int taskIndexToDone) {
        this.taskIndexToDone = taskIndexToDone;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;

        for (int i = 1; i <= this.taskList.getNumTasks(); i++) {
            if (i == taskIndexToDone) {
                this.taskList.getNthTask(i).completeTask();
                this.taskToDone = this.taskList.getNthTask(i);
            }
        }
        return this.taskList;
    }

    @Override
    public String getResponse() {
        return "____________________________________________________________\n" +
                "Nice! I've marked this task as done: \n  " +
                this.taskToDone.toString() +
                "\n____________________________________________________________\n";
    }
}
