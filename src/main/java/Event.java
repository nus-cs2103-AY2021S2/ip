/*
 * Event class to handle tasks that starts at a specific date/time and ends at a specific date/time
 */

public class Event extends Task {
    protected final String at;

    /**
     * Constructor for Event class
     * @param description details of the task
     * @param at starts and ends at specific date/time to complete the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String formatTask() {
        return String.format("E | %s", super.formatTask());
    }

    /**
     * Displays task type, description and timeframe of tasks
     * @return String format regarding the Event details
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
