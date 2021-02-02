package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class TodoHandler implements CommandHandler {
    Todo toAdd;
    String response;

    public TodoHandler(String todoDes) {
        toAdd = new Todo(todoDes);
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        taskList.addTask(toAdd);
        response = "Got it. I've added this task:\n"
                + " " + toAdd + "\n"
                + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.\n";
        ui.respond(response);
        storage.addTask(toAdd);
    }
}
