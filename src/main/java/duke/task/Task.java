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
    boolean hasTime = false;

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
        String result = "";
        if (getSizeOfCommand() > 2 &&
                (divideCommand[2].equals("/at") || divideCommand[2].equals("/by"))) {
            result += divideCommand[1];
        } else if (getSizeOfCommand() == 2) {
            result += divideCommand[1];
        } else {
            result += divideCommand[1] + " " + divideCommand[2];
        }
        return result;
    }

    /**
     * returns the date of task to be completed
     * applicable to deadlines and events tasks
     *
     * @return the date of the task
     */
    public LocalDate getDate() {
        String StringDate = "";
        if (getSizeOfCommand() == 6 &&
                (divideCommand[3].equals("/at") || divideCommand[3].equals("/by"))) {
            StringDate += divideCommand[4];
            hasTime = true;
        } else if (getSizeOfCommand() == 5 &&
                (divideCommand[3].equals("/at") || divideCommand[3].equals("/by"))) {
            StringDate += divideCommand[4];
            hasTime = false;
        } else if (getSizeOfCommand() == 5 &&
                (divideCommand[2].equals("/at") || divideCommand[2].equals("/by"))) {
            StringDate += divideCommand[3];
            hasTime = true;
        } else if (getSizeOfCommand() == 4 &&
                (divideCommand[2].equals("/at") || divideCommand[2].equals("/by"))) {
            StringDate += divideCommand[3];
            hasTime = false;
        }
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
        String StringTime = "";
        if (hasTime) {
            if (getSizeOfCommand() == 6) {
                StringTime += divideCommand[5];
            } else if (getSizeOfCommand() == 5 &&
                    (divideCommand[2].equals("/at") || divideCommand[2].equals("/by"))) {
                StringTime += divideCommand[4];
            }
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
