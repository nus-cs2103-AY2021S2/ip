package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteHandler implements CommandHandler {
    private int taskNum;
    private String response;

    public DeleteHandler(int taskNum) {
        this.taskNum = taskNum;
    }

    public int getTaskNum() {
        return taskNum;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        response = execute(ui, storage, taskList, true);
        ui.respond(response);
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList, boolean toString) {
        assert toString = true;
        Task task = taskList.getTask(taskNum);
        taskList.removeTask(taskNum);
        response = "Noted. I've removed this task: \n"
                + " " + task + "\n";
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
}
