package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeInvalidDesException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DoneHandler implements CommandHandler{
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
        taskList.markDone(taskNum);
        Task updatedTask = taskList.getTask(taskNum);
        response = "Nice! I've marked this task as done: \n"
                + " " + updatedTask+ "\n";
        ui.respond(response);
        storage.markDoneInStorage(updatedTask, taskNum);
    }
}
