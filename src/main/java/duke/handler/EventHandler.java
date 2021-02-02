package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.tasks.Event;
import duke.tasks.TaskList;

import java.time.LocalDateTime;

public class EventHandler implements CommandHandler {
    private Event toAdd;
    private String response;

    public EventHandler(String eventDes, LocalDateTime dateTimeAt) {
        toAdd = new Event(eventDes, dateTimeAt);
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
