package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Deadline duke.tasks.Task.
 *
 *  @author Yap Jing Kang
 */


public class DeadlineTask extends Task {
    protected LocalDate deadline;

    /**
     *  duke.tasks.DeadlineTask constructor.
     *
     *  @param name Name of duke.tasks.DeadlineTask.
     *  @param deadline Specified deadline of task.
     */
    public DeadlineTask(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    public DeadlineTask(String name, LocalDate deadline, boolean isCompleted) {
        super(name);
        this.deadline = deadline;
        if (isCompleted) {
            this.markAsDone();
        }
    }

    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        return String.format("%s|%s|%s|%s",
                "D",
                done == Status.DONE ? "1" : "0",
                name,
                deadline.format(formatter));
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
