import java.time.LocalDateTime;

public class EventCommand extends Command {

    private Event event;

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
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        tasks.addTask(this.event);
        ui.printAddTaskAck(this.event, tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
