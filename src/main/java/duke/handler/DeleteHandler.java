package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteHandler implements CommandHandler{
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
        Task task = taskList.getTask(taskNum);
        taskList.removeTask(taskNum);
        response = "Noted. I've removed this task: \n"
                + " " + task + "\n";
        ui.respond(response);
        storage.delete(taskNum);
    }
}