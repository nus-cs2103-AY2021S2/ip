import java.time.LocalDateTime;

public class CommandDeadline extends Command {
    private final String description;
    private final LocalDateTime dateTime;

    public CommandDeadline(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, dateTime);
        tasks.addTask(deadline);
        storage.save(tasks);
        ui.printCommand(this);
        ui.printTask(deadline);
    }

    @Override
    public String toDukeOutput() {
        return "Roger that boss, I've added a new Deadline: ";
    }
}
