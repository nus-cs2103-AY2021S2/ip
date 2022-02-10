package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddEventCommand extends Command {
    private Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = ui.getAddMessage(tasks, tasks.addTask(event));
        storage.save(tasks);
        return message;
    }
}

