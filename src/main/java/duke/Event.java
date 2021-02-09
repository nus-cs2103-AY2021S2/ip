package duke;

/**
 * Event is a type of task that has a name and a date/time.
 */
public class Event extends Task {
    private String info;

    /**
     * Only constructor for event that checks it's validity and sets the info instance variable.
     *
     * @param taskLine the entire scanned line from the input
     * @throws ArrayIndexOutOfBoundsException
     * @throws IllegalArgumentException
     */
    protected Event(String taskLine) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        super(taskLine);
        checkTask(taskLine);
        buildInfo();
    }


    /**
     * Checks the validity of the event task.
     *
     * @param taskLine
     * @throws ArrayIndexOutOfBoundsException if there is no body to the deadline task.
     * @throws IllegalArgumentException       if there is no '/by' indicator.
     */
    protected void checkTask(String taskLine) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (taskLine.length() < 2) {
            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The description of a Event cannot be empty.");
        } else if (!taskLine.contains("/at")) {
            throw new IllegalArgumentException("☹ OOPS!!! The Event needs an '/at' time.");
        }
    }

    private void buildInfo() {
        assert !taskLine.equals("");
        String[] parsedTask = taskLine.split("event");
        parsedTask = parsedTask[1].split("/at");
        this.name = parsedTask[0].strip();
        this.dateTime = parsedTask[1].strip();
        setDateTimeLD(dateTime);
        this.info = name + " at: " + dateTime;
    }

    protected String printNew() {
        return "[E][ ] " + info;
    }

    public String toString() {
        return info;
    }

    @Override
    protected String type() {
        return "E";
    }
}