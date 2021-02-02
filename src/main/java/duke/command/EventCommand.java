package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents an 'event' command.
 * Adds a new event to the task list.
 */
public class EventCommand extends Command {

    public EventCommand(String arguments) {
        super(arguments);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        if (getArguments().isBlank()) {
            throw new DukeException("I apologize, please input description and time for 'event'.");
        } else {
            String[] split = getArguments().split("/at");
            if (getArguments().equals(split[0])) {
                throw new DukeException("I apologize, please use '/at' argument to specify time for 'event'.");
            } else {
                Task newTask = new Event(split[0].strip(), split[1].strip());
                taskList.add(newTask);
                storage.addToFile(newTask);
                return "Added to to-do list: \n" + newTask;
            }
        }
    }
}
