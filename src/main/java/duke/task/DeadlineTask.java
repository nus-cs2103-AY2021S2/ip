package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  DeadlineTask.
 *
 *  @author Yap Jing Kang
 */

public class DeadlineTask extends Task {
    protected LocalDate deadline;

    /**
     *  DeadlineTask constructor.
     *
     *  @param name Name of duke.tasks.DeadlineTask.
     *  @param deadline Specified deadline of task.
     */
    public DeadlineTask(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     *  DeadlineTask constructor. For use with persistent storage.
     *
     *  @param name Name of DeadlineTask.
     *  @param deadline Specified deadline of task.
     *  @param isCompleted Whether DeadlineTask is completed, or not.
     */
    public DeadlineTask(String name, LocalDate deadline, boolean isCompleted) {
        super(name);
        this.deadline = deadline;
        if (isCompleted) {
            this.markAsDone();
        }
    }

    /**
     *  Converts DeadlineTask object to String suitable for storage.
     *
     *  @return String DeadlineTask information in a file-friendly format
     */
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
