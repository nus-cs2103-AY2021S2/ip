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

    @Override
    public String toString() {
        return String.format("[E][%s] %s (by: %s)",
                done == Status.DONE ? "X" : " ",
                name,
                duration);
    }
}
