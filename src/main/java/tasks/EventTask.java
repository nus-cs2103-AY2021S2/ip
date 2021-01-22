package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Event tasks.Task.
 *
 *  @author Yap Jing Kang
 */


public class EventTask extends Task {
    protected LocalDate duration;

    /**
     *  tasks.EventsTask constructor.
     *
     *  @param name Name of tasks.EventsTask.
     *  @param duration Specified duration of task.
     */
    public EventTask(String name, LocalDate duration) {
        super(name);
        this.duration = duration;
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
