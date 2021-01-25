package Command;
import Mike.TaskList;
import Task.*;
import Exception.MikeInvalidInputException;

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
        return String.format(
                "Nice! I've marked this task as done: \n" +
                "  %s",
                this.taskToDone.toString());

    }
}
