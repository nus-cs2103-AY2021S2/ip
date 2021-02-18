package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {

    private final Event event;

    private static final String NO_ARGUMENT_ERROR = "Please specify a valid event!";

    public EventCommand(String description) throws DukeException {
        try {
            if (description.isEmpty()) {
                throw new DukeException(NO_ARGUMENT_ERROR);
            }
            String[] descArr = description.split(" /at ");
            if (descArr.length < 2) {
                throw new DukeException(NO_ARGUMENT_ERROR);
            }
            LocalDateTime time = Parser.parseInputToDateTime(descArr[1]);
            this.event = new Event(descArr[0], time);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.event);
        storage.write(tasks);
        return ui.getAddTaskAck(this.event, tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
