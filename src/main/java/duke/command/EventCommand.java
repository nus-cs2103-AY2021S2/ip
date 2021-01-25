package duke.command;

import duke.Ui;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.DukeException;
import duke.storage.Storage;

public class EventCommand extends Command {

    public EventCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        Task task = Event.createEvent(command);
        TaskList.addTask(task, Storage.getTasks());
        Ui.displayAddedTask(task, Storage.getTasks());
        Storage.updateDataFile();
    }
}
