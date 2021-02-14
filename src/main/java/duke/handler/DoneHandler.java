package duke.handler;

import duke.Storage;
import duke.Ui;
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
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        response = execute(ui, storage, taskList, true);
        ui.respond(response);
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList, boolean toString) {
        assert toString = true;
        taskList.markDone(taskNum);
        Task updatedTask = taskList.getTask(taskNum);
        response = "Nice! I've marked this task as done: \n"
                + " " + updatedTask + "\n";
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
}
