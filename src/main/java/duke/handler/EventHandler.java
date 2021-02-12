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
        return toAdd;
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
