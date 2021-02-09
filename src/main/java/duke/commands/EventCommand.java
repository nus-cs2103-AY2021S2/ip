package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class EventCommand extends Command{
    private String eventDescription;
    private String period;

    public EventCommand(String eventDescription, String period) {
        this.eventDescription = eventDescription;
        this.period = period;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task event = new Event(eventDescription, period);
            tasks.addTask(event);
            ui.showAddTask(event, tasks);
            storage.saveFile(tasks.getTaskList());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
