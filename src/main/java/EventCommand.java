import java.time.LocalDateTime;

public class EventCommand extends Command {
    static final String COMMAND_WORD = "event";
    private String task;
    private LocalDateTime dateTime;

    public EventCommand(TaskList taskList, Ui ui, Storage storage, String task, LocalDateTime dateTime) {
        super(taskList, ui, storage);
        this.task = task;
        this.dateTime = dateTime;
    }

    @Override
    public void execute() {
        Event e = new Event(this.task, this.dateTime);
        System.out.println("Got it. I've added this task:\n" + e);
        this.taskList.addTask(e);
    }
}
