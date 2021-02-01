package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is used to store information about tasks for use by Duke.
 */
public class Task {
    protected static final DateTimeFormatter INPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    protected final String name;
    protected boolean isDone;
    protected final LocalDate date;

    /**
     * Initialises task using task description.
     *
     * @param name Description of task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.date = calculateDate(name);
    }

    /**
     * Calculates the date given a task description. Returns null if no date is given.
     *
     * @param name Task description.
     * @return Date if specified in task description.
     */
    public LocalDate calculateDate(String name) {
        String[] inputs = name.substring(0, name.length() - 1).split(": ");
        if (inputs.length > 1) {
            String inputDate = inputs[1].split(" ")[0];
            return LocalDate.parse(inputDate, Task.INPUT_DATE_FORMAT);
        }
        return null;
    }

    /**
     * Sets task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Provides the task description with a reformatted date if applicable.
     *
     * @return Reformatted description of task.
     */
    @Override
    public String toString() {
        String result = this.name;

        // Reformat date before converting to string
        if (this.date != null) {
            result = this.name.split(": ")[0];
            String[] outputs = this.name.substring(0, name.length() - 1)
                    .split(": ")[1].split(" ");
            outputs[0] = this.date.format(Task.OUTPUT_DATE_FORMAT);
            result += ": " + outputs[0];
            for (int i = 1; i < outputs.length; i++) {
                result += " " + outputs[i];
            }
            result += ")";
        }

        if (this.isDone) {
            return "[X] " + result;
        } else {
            return "[ ] " + result;
        }
    }
}
