package duke.commands;

import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime atDateTime;

    public AddEventCommand(String description, LocalDateTime atDateTime) {
        this.description = description;
        this.atDateTime = atDateTime;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui) {
        Event event = new Event(this.description, this.atDateTime);
        tasks.addTask(event);
        ui.handleAddTask(tasks, event);
    }
}
