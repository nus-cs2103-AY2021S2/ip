package command;
import exception.MikeCommandExecutionException;
import mike.TaskList;
import task.Task;

public class DoneCommand implements Command {

    private int taskIndexToDone;
    private Task taskToDone;
    private TaskList taskList;

    public DoneCommand(int taskIndexToDone) {
        this.taskIndexToDone = taskIndexToDone;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Execute command and marks the task in the provided taskList as done
     * @param taskList TaskList object that contains the task to mark done
     * @return TaskList object after marking task as done in list
     * @throws MikeCommandExecutionException if task to be marked done does not exist in the taskList provided
     */
    @Override
    public TaskList runCommand(TaskList taskList) throws MikeCommandExecutionException {
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
            throw new MikeCommandExecutionException("DoneCommand", "The task is not in the list");
        }

    }

    @Override
    public String getResponse() {
        return String.format(
                "Nice! I've marked this task as done: \n"
                        + "  %s",
                this.taskToDone.toString());

    }
}
