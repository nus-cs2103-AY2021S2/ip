package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {

    public TodoCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        Task task = Todo.createTodo(command);
        TaskList.addTask(task);
    }
}
