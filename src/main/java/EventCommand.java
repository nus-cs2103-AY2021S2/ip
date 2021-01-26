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
            this.event = new Event(descArr[0], descArr[1]);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.event);
        ui.printAddTaskAck(this.event, tasks);
        storage.write(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
