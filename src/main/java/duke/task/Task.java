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
    String[] taskPart;
    String[] datePart;
    boolean hasTime = false;

    Task(String task) {
        this.task = task;
        if (task.split(" ")[0].equals("add")) {
            divideCommand = task.split(" ");
        } else if (!task.split(" ")[0].equals("todo")) {
            divideCommand = task.split("/");
            taskPart = divideCommand[0].split(" ");
            datePart = divideCommand[1].split(" ");
        }
    }

    /**
     * Returns the type of task which is the first element of the array
     *
     * @return
     */
    public String getType() {
        return task.split(" ")[0];
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
        for (int i = 1; i < taskPart.length; i++) {
            result += i == taskPart.length - 1
                    ? taskPart[i] : taskPart[i] + " ";
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
        StringDate = datePart[1];
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
        /**
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
         }**/
        if (datePart.length > 2) {
            StringTime = datePart[2];
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
