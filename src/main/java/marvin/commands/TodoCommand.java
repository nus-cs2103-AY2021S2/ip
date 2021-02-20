package marvin.commands;

import marvin.exception.DukeException;
import marvin.message.Messages;
import marvin.storage.Storage;
import marvin.task.Task;
import marvin.task.TaskList;
import marvin.task.Todo;

/**
 * Represents a user command which adds a to-do to the task list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private String description;

    /**
     * Constructor takes in the description of a to-do.
     * @param description The description of the to-do.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the user command and returns a response message
     * @param tasks The state of the current task list.
     * @param storage The storage used by the program.
     * @return Response message.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;
        assert description != null;

        Task newTask = new Todo(description);
        tasks.add(newTask);
        storage.save(tasks);
        return Messages.getAddTaskMessage(newTask, tasks.size());
    }
}
