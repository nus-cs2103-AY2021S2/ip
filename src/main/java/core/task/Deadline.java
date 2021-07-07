package core.task;

/**
 * Encapsulates a Deadline type Task.
 */
public class Deadline extends TimedTask {


    /**
     * Creates a new Deadline task with a description. Must contain '/by' after which should be the event time.
     * @param desc - description
     */
    public Deadline(String desc) {
        super(desc, "/by");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + customFormat() + ")";
    }
}
