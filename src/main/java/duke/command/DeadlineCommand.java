package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tag.Tag;
import duke.task.Deadline;
import duke.task.Task;
import duke.util.Tuple;

/**
 * Represents a 'deadline' command.
 * Adds a new deadline to the task list.
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        if (getArguments().isBlank()) {
            throw new DukeException("I apologize, please input description and time for 'deadline'.");
        } else {
            Tuple<String, ArrayList<Tag>> argsAndTags = Tag.retrieveTags(getArguments());
            String[] splitByDate = argsAndTags.getFirst().split("/by");
            if (getArguments().equals(splitByDate[0])) {
                throw new DukeException("I apologize, please use '/by' argument "
                        + "to specify date (format: yyyy-MM-dd) and optionally time for 'deadline'.");
            } else {
                Task newTask = new Deadline(splitByDate[0].strip(), splitByDate[1].strip(), argsAndTags.getSecond());
                taskList.add(newTask);
                storage.addToFile(newTask);
                return "Added to to-do list: \n" + newTask;
            }
        }
    }
}
