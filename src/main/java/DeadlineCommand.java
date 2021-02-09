import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    String by;

    public DeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Deadline deadline = new Deadline(description, by);
            taskList.addDeadlineTask(deadline);
            storage.writeToFile(taskList.getList());
            ui.showTaskAdded(deadline);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date entered!");
        }

    }
}
