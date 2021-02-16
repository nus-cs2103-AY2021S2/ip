package duke.handler;

import java.time.LocalDateTime;

import duke.Storage;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.TaskList;


public class DeadlineHandler implements CommandHandler {
    private Deadline toAdd;
    private String response;

    public DeadlineHandler(String deadlineDes, LocalDateTime dateTimeBy) {
        toAdd = new Deadline(deadlineDes, dateTimeBy);
    }

    public Deadline getDeadlineTask() {
        assert toAdd instanceof Deadline;
        Deadline deadlineTask = (Deadline) toAdd;
        return deadlineTask;
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
        } else if (obj instanceof DeadlineHandler) {
            return toAdd.equals(((DeadlineHandler) obj).getDeadlineTask());
        } else {
            return false;
        }
    }
}
