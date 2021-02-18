package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Basic task class from which the specific tasks are formed
 */
public class Task {
    String task;
    boolean done = false;
    String[] divideCommand;

    Task(String task) {
        this.task = task;
        divideCommand = task.split(" ");
    }

    /**
     * Returns the type of task which is the first element of the array
     *
     * @return
     */
    public String getType() {
        return divideCommand[0];
    }

    /**
     * Marks a task as done
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Checks if a task is done
     *
     * @return status of task
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Returns the full task
     *
     * @return entire task/command
     */
    public String getTaskName() {
        return this.task;
    }

    /**
     * Gets only the task name
     *
     * @return just the task name
     */
    public String getName() {
        return divideCommand[1].equals("-") ? divideCommand[2]
                : divideCommand[1] + " " + divideCommand[2];
    }

    /**
     * returns the date of task to be completed
     * applicable to deadlines and events tasks
     *
     * @return the date of the task
     */
    public LocalDate getDate() {
        String StringDate = divideCommand[4];
        return LocalDate.parse(StringDate);
    }

    /**
     * Formats the date according to the specification
     *
     * @return the formatted date
     */
    public String getDateFormat() {
        return getDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the size of the command
     *
     * @return
     */
    public int getSizeOfCommand() {
        return divideCommand.length;
    }

    /**
     * Returns the time of the task to be completed
     *
     * @return
     */
    public LocalTime getTime() {
        if (getSizeOfCommand() > 5) {
            String StringTime = divideCommand[5];
            return LocalTime.parse(StringTime);
        } else {
            return null;
        }
    }

    /**
     * Structures the time in the relevant format
     *
     * @return
     */
    public String getTimeFormat() {
        return getTime() == null ? ""
                : getTime().format(DateTimeFormatter.ofPattern("hh:mma"));
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[X] " + this.task;
        } else {
            return "[-] " + this.task;
        }
    }
}
