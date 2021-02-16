package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tag.Tag;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Tuple;

import java.util.ArrayList;

/**
 * Represents a 'todo' command.
 * Adds a new todo to the task list.
 */
public class TodoCommand extends Command {

    public TodoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        if (getArguments().isBlank()) {
            throw new DukeException("I apologize, please input description for 'todo'.");
        } else {
            Tuple<String, ArrayList<Tag>> argsAndTags = Tag.retrieveTags(getArguments());
            Task newTask = new Todo(argsAndTags.getFirst(), argsAndTags.getSecond());
            taskList.add(newTask);
            storage.addToFile(newTask);
            return "Added to to-do list: \n" + newTask;
        }
    }
}
