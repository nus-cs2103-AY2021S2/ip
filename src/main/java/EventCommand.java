import java.time.LocalDateTime;

public class EventCommand extends Command{
    private String desc = "";
    private LocalDateTime start;
    private LocalDateTime end;

    public EventCommand(String desc, LocalDateTime start, LocalDateTime end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() throws DukeCommandException {
        try {
            Event event = taskManager.addEvent(this.desc, this.start, this.end);
            ui.printAddMsg(event, taskManager.getTasksSize());
            Storage.saveTasks(taskManager.getTasks());
        } catch (DukeException e) {
            throw new DukeCommandException("event", desc, e.getMessage());
        }
    }
}