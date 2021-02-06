package duke.task;

import java.time.LocalDate;
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

    public String getType(){
        return divideCommand[0];
    }

    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getTaskName() {
        return this.task;
    }

    public String getName() {
        return divideCommand[1] + " " + divideCommand[2];
    }

    public LocalDate getDate() {
        String StringDate = "";
        for (int i = 4; i < divideCommand.length; i++) {
            if (i == divideCommand.length - 1) {
                StringDate += divideCommand[i];
            } else {
                StringDate += divideCommand[i] + " ";
            }
        }
        return LocalDate.parse(StringDate);
    }

    public String getDateFormat() {
        return getDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
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
