package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private final Deadline deadline;

    private static final String NO_ARGUMENT_ERROR = "Please specify a deadline!";
    private static final String INVALID_DATE_ERROR = "Please specify a valid date!";

    public DeadlineCommand(String description) throws DukeException {
        try {
            if (description.isBlank()) {
                throw new DukeException(NO_ARGUMENT_ERROR);
            }
            String[] descArr = description.split(" /by ");
            if (descArr.length < 2) {
                throw new DukeException(NO_ARGUMENT_ERROR);
            }
            LocalDateTime time = Parser.parseInputToDateTime(descArr[1]);
            this.deadline = new Deadline(descArr[0], time);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_DATE_ERROR);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.deadline);
        storage.write(tasks);
        return ui.getAddTaskAck(this.deadline, tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
