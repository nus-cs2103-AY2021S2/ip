package duke.command;

import duke.DukeException;
import duke.Messages;
import duke.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String description;
    private String date;

    public DeadlineCommand(String description, String date) {
        super();
        this.description = description;
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;
        assert description != null;
        assert date != null;

        Task newTask = new Deadline(description, date);
        tasks.add(newTask);
        return Messages.getAddTaskMessage(newTask, tasks.size());
    }
}
