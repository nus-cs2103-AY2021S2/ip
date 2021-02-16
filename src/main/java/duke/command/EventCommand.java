package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tag.Tag;
import duke.task.Event;
import duke.task.Task;
import duke.util.Tuple;

import java.util.ArrayList;

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
            Tuple<String, ArrayList<Tag>> argsAndTags = Tag.retrieveTags(getArguments());
            String[] splitByDate = argsAndTags.getFirst().split("/at", -1);
            if (getArguments().equals(splitByDate[0])) {
                throw new DukeException("I apologize, please use '/at' argument to specify time for 'event'.");
            } else {
                Task newTask = new Event(splitByDate[0].strip(), splitByDate[1].strip(), argsAndTags.getSecond());
                taskList.add(newTask);
                storage.addToFile(newTask);
                return "Added to to-do list: \n" + newTask;
            }
        }
    }
}
