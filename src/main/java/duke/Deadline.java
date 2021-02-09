package duke;

/**
 * Deadline is a type of task that has a name and a date/time.
 */
public class Deadline extends Task {
    private String info;

    /**
     * Only deadline constructor that checks it's validity and sets the info instance variable.
     *
     * @param taskLine the entire scanned line from the input
     * @throws ArrayIndexOutOfBoundsException if there is no '/by' indicator
     */
    Deadline(String taskLine) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        super(taskLine);
        checkTask(taskLine);
        setVar();
    }

    /**
     * Checks the validity of the deadline task.
     *
     * @param taskLine
     * @throws ArrayIndexOutOfBoundsException if there is no body to the deadline task
     * @throws IllegalArgumentException       if there is no '/by' indicator
     */
    private void checkTask(String taskLine) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (taskLine.length() < 2) {
            throw new ArrayIndexOutOfBoundsException("☹The description of a Deadline cannot be empty.");
        } else if (!taskLine.contains("/by")) {
            throw new IllegalArgumentException("☹ OOPS!!! The Deadline needs an '/by' time.");
        }
    }

    private void setVar() {
        String[] parsedTask = buildInfo();
        this.name = parsedTask[0].strip();
        this.dateTime = parsedTask[1].strip();
        this.info = name + " by: " + dateTime;
        setDateTimeLD(dateTime);
    }

    private String[] buildInfo() {
        String[] parsedTask = taskLine.split("deadline");
        parsedTask = parsedTask[1].split("/by");
        return parsedTask;

    }


    protected String printNew() {
        return "[D][ ] " + info;
    }

    @Override
    public String toString() {
        return info;
    }

    @Override
    protected String type() {
        return "D";
    }
}
