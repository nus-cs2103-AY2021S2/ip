import java.time.LocalDate;

public class EventCommand extends AddCommand {
    private String taskString;
    private LocalDate date;

    EventCommand(String taskString, LocalDate date, TaskList tasks) {
        super.tasks = tasks;
        this.taskString = taskString;
        this.date = date;
    }

    public String getString() {
        Event event = new Event(taskString, date);
        tasks.add(event);
        return event.toString();
    }
}
