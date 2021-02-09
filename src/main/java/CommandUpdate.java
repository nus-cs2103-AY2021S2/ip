import java.time.LocalDate;
import java.time.LocalTime;

public class CommandUpdate extends Command {
    private final int index;
    private final String description;
    private final LocalDate date;
    private final LocalTime time;

    public CommandUpdate(int index, String description, LocalDate date, LocalTime time) {
        this.index = index;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.getTasks().get(index - 1);

        if (description != null) {
            task.changeDescription(description);
        }

        if (task.isTimed()) {
            TimedTask timedTask = (TimedTask) task;

            if (date != null) {
                timedTask.changeDate(date);
            }

            if (time != null) {
                timedTask.changeTime(time);
            }
        }

        storage.save(tasks);

        return toDukeOutput() + task;
    }

    public String toDukeOutput() {
        return "Task updated successfully: ";
    }
}
