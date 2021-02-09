import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    String at;

    public EventCommand(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Event event = new Event(description, at);
            taskList.addEventTask(event);
            storage.writeToFile(taskList.getList());
            ui.showTaskAdded(event);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date entered! Please type in valid yyyy-mm-dd format!");
        }
    }
}
