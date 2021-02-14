package duke.commands;

import static duke.commands.CommandUtils.checkDateTimeConflicts;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.ParserUtils;
import duke.TaskList;
import duke.models.Deadline;
import duke.models.Task;

/**
 * Handles the Deadline command of the user to create new deadlines in the list.
 * Format of command: "deadline [deadline_name] /by [deadline]".
 */
public class DeadlineCommand extends AddCommand {
    private final LocalDateTime deadline;

    /**
     * Command relating to creating a new Deadline
     *
     * @param taskName name of the deadline
     * @param deadline deadline of the specified task
     */
    protected DeadlineCommand(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public Task getTask() {
        return new Deadline(getTaskName(), deadline);
    }

    @Override
    public boolean isTaskValid(Task task, TaskList tasks) throws DukeException {
        assert (task instanceof Deadline);
        boolean isConflict = checkDateTimeConflicts(((Deadline) task).getDeadline(), tasks);
        if (isConflict) {
            throw new DukeException("You have another deadline / event ongoing at the given datetime.");
        }
        return true;
    }

    /**
     * Creates a new instance of Deadline Command
     *
     * @param argString string with argument
     * @return instance of Deadline Command
     * @throws DukeException
     */
    public static DeadlineCommand buildInstance(String argString) throws DukeException {
        String[] cmdArgs = ParserUtils.getCommandArgs(argString, "The description of a todo cannot be empty.");

        assert (cmdArgs[0].equals("deadline"));

        String[] deadlineArgs = cmdArgs[1].split(" /by ", 2);
        if (deadlineArgs.length < 2) {
            throw new DukeException("The deadline needs to have a date specified with \"/by\".");
        }
        String taskName = deadlineArgs[0];
        LocalDateTime deadline = ParserUtils.parseDateTime(deadlineArgs[1],
            "The deadline needs to be specified in a valid date format.");
        return new DeadlineCommand(taskName, deadline);
    }
}
