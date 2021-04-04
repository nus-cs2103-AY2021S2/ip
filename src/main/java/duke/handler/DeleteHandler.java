package duke.handler;

import duke.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteHandler implements CommandHandler {
    private int taskNum;
    private String response;

    /**
     * Default constructor for delete handler.
     * @param taskNum
     */
    public DeleteHandler(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Returns the number of the task to be deleted.
     * @return The number of the task.
     */
    public int getTaskNum() {
        return taskNum;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        Task task = taskList.getTask(taskNum);
        taskList.removeTask(taskNum);
        response = deleteRespond(task);
        storage.delete(taskNum);
        return response;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DeleteHandler) {
            return taskNum == ((DeleteHandler) obj).getTaskNum();
        } else {
            return false;
        }
    }

    /**
     * Returns string response for deletion.
     * @param toDelete Task to be deleted.
     * @return String response.
     */
    public String deleteRespond(Task toDelete) {
        String deleteResponse = "Noted. I've removed this task: \n"
                + " " + toDelete + "\n";
        return deleteResponse;
    }
}
