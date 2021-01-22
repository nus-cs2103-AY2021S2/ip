package tasks;

/**
 *  Event tasks.Task.
 *
 *  @author Yap Jing Kang
 */


public class EventTask extends Task {
    protected String duration;

    /**
     *  tasks.EventsTask constructor.
     *
     *  @param name Name of tasks.EventsTask.
     *  @param duration Specified duration of task.
     */
    public EventTask(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    public EventTask(String name, String duration, boolean isCompleted) {
        super(name);
        this.duration = duration;
        if (isCompleted) {
            this.markAsDone();
        }
    }

    public String toFileFormat() {
        return String.format("%s|%s|%s|%s",
                "E",
                done == Status.DONE ? "1" : "0",
                name,
                duration);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (by: %s)",
                done == Status.DONE ? "X" : " ",
                name,
                duration);
    }
}
