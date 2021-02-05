package Commands;

import Tasks.Event;
import Tasks.TaskList;

import java.time.LocalDateTime;

public class AddEventCommand extends AddCommand {
    private final String description;
    private final LocalDateTime at;

    public AddEventCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    public void execute(TaskList tasks) {
        Event event = new Event(this.description, this.at);
        this.executeAddTask(tasks, event);
    }
}
