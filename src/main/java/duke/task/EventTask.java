package duke.task;

/**
 * This class handles the eventTask.
 */
public class EventTask extends Task {

    /**
     * Constructor for event
     * @param task
     */
    public EventTask(String task) {
        super(task);
    }


    /**
     * String representation of event
     * @return
     */
    @Override
    public String toString() {
        String taskRepresent = getName() + " (" + super.divideCommand[3].substring(1)
                + ": " + getDateFormat() + " " + getTimeFormat() + ")";
        if (this.isDone()) {
            return "[E][X] " + taskRepresent;
        } else {
            return "[E][-] " + taskRepresent;
        }
    }
}
