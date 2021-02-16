package duke;

public class Events extends Task {
    String time;

    /**
     * This constructor create a Task which is of type Events
     * @param isDone whether the task is already completed
     * @param eventName description of the event
     * @param time the time of which the Event will take place
     * @return return the task object
     */
    public Events(boolean isDone, String eventName, String time) {

        super(isDone, eventName, "E");
        this.time = time;
    }

    @Override
    public String toString() {

        return "[E] " + super.toString() + " (at: " + time + ")";
    }
}
