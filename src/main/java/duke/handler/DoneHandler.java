package duke.handler;

import duke.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DoneHandler implements CommandHandler {
    private int taskNum;
    private String response;

    public DoneHandler(int taskNum) {
        this.taskNum = taskNum;
    }

    public int getTaskNum() {
        return taskNum;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        taskList.markDone(taskNum);
        Task updatedTask = taskList.getTask(taskNum);
        response = doneRespond(updatedTask);
        storage.markDoneInStorage(updatedTask, taskNum);
        return response;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DoneHandler) {
            return taskNum == ((DoneHandler) obj).getTaskNum();
        } else {
            return false;
        }
    }

    /**
     * Resturns string response for done handled.
     * @param doneTask The task to be marked done.
     * @return String response.
     */
    public String doneRespond(Task doneTask) {
        String doneResponse = "Nice! I've marked this task as done: \n"
                + " " + doneTask + "\n";
        return doneResponse;
    }
}
