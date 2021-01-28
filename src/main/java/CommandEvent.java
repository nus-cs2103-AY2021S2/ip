import java.time.LocalDateTime;

public class CommandEvent extends Command {
    private final String description;
    private final LocalDateTime dateTime;

    public CommandEvent(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, dateTime);
        tasks.addTask(event);
        storage.save(tasks);
        ui.printCommand(this);
        ui.printTask(event);
    }

    @Override
    public String toDukeOutput() {
        return "Roger that boss, I've added a new Event: ";
    }
}
