package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Event duke.tasks.Task.
 *
 *  @author Yap Jing Kang
 */


public class EventTask extends Task {
    protected LocalDate duration;

    /**
     *  duke.tasks.EventsTask constructor.
     *
     *  @param name Name of duke.tasks.EventsTask.
     *  @param duration Specified duration of task.
     */
    public EventTask(String name, LocalDate duration) {
        super(name);
        this.duration = duration;
    }

    public EventTask(String name, LocalDate duration, boolean isCompleted) {
        super(name);
        this.duration = duration;
        if (isCompleted) {
            this.markAsDone();
        }
    }

    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        return String.format("%s|%s|%s|%s",
                "E",
                done == Status.DONE ? "1" : "0",
                name,
                duration.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        return String.format("[E][%s] %s (by: %s)",
                done == Status.DONE ? "X" : " ",
                name,
                duration.format(formatter));
    }
}
