import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    String by;

    public DeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Deadline deadline = new Deadline(description, by);
            taskList.addDeadlineTask(deadline);
            storage.writeToFile(taskList.getList());
            ui.showTaskAdded(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date entered! Please type in valid yyyy-mm-dd format!");
        }
    }
}
