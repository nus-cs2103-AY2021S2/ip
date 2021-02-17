package duke.commands;

import duke.exception.DukeException;
import duke.message.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a user command which adds a deadline to the task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String description;
    private String date;

    /**
     * Constructor takes in the description of a deadline and the by date.
     * @param description The description of the deadline.
     * @param date The by date.
     */
    public DeadlineCommand(String description, String date) {
        super();
        this.description = description;
        this.date = date;
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
        assert date != null;

        Task newTask = new Deadline(description, date);
        tasks.add(newTask);
        storage.save(tasks);
        return Messages.getAddTaskMessage(newTask, tasks.size());
    }
}
