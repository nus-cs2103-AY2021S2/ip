package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Deadline tasks.Task.
 *
 *  @author Yap Jing Kang
 */


public class DeadlineTask extends Task {
    protected LocalDate deadline;

    /**
     *  tasks.DeadlineTask constructor.
     *
     *  @param name Name of tasks.DeadlineTask.
     *  @param deadline Specified deadline of task.
     */
    public DeadlineTask(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        return String.format("[D][%s] %s (by: %s)",
                done == Status.DONE ? "X" : " ",
                name,
                deadline.format(formatter));
    }
}
