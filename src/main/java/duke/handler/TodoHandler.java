package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class TodoHandler implements CommandHandler {
    private Todo toAdd;
    private String response;

    public TodoHandler(String todoDes) {
        toAdd = new Todo(todoDes);
    }

    public Todo getTodoTask() {
        assert toAdd instanceof Todo;
        Todo todoTask = (Todo) toAdd;
        return todoTask;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        response = execute(ui, storage, taskList, true);
        ui.respond(response);
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList, boolean toString) {
        assert toString = true;
        taskList.addTask(toAdd);
        response = "Got it. I've added this task:\n"
                + " " + toAdd + "\n"
                + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.\n";
        storage.addTask(toAdd);
        return response;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof TodoHandler) {
            return toAdd.equals(((TodoHandler) obj).getTodoTask());
        } else {
            return false;
        }
    }
}

