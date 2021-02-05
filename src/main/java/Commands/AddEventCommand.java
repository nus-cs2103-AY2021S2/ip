package Commands;

import Tasks.Event;
import Tasks.TaskList;
import UserInterface.Ui;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime at;

    public AddEventCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui) {
        Event event = new Event(this.description, this.at);
        tasks.addTask(event);
        ui.handleAddTask(tasks, event);
    }
}
