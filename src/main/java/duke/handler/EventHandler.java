package duke.handler;

import java.time.LocalDateTime;

import duke.Storage;
import duke.Ui;
import duke.tasks.Event;
import duke.tasks.TaskList;


public class EventHandler implements CommandHandler {
    private Event toAdd;
    private String response;

    public EventHandler(String eventDes, LocalDateTime dateTimeAt) {
        toAdd = new Event(eventDes, dateTimeAt);
    }

    public Event getEventTask() {
        assert toAdd instanceof Event;
        Event eventTask = (Event) toAdd;
        return eventTask;
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
        } else if (obj instanceof EventHandler) {
            return toAdd.equals(((EventHandler) obj).getEventTask());
        } else {
            return false;
        }
    }
}
