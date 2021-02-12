import java.time.LocalDate;

public class DeadlineCommand extends AddCommand {
    private String taskString;
    private LocalDate date;

    DeadlineCommand(String taskString, LocalDate date, TaskList tasks) {
        super.tasks = tasks;
        this.taskString = taskString;
        this.date = date;
    }

    public String getString() {
        Deadline deadline = new Deadline(taskString, date);
        tasks.add(deadline);
        return deadline.toString();
    }
}
