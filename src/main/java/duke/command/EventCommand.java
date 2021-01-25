package duke.command;

import duke.DukeException;
import duke.data.Data;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import static duke.data.Data.updateDataFile;

public class EventCommand extends Command {

    public EventCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        Task task = Event.createEvent(command);
        TaskList.addTask(task, Data.getTasks());
        updateDataFile();
    }
}
