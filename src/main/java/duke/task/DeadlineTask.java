package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class handles the deadlineTasks
 */
public class DeadlineTask extends Task {
    String[] divideCommand;

    public DeadlineTask(String task) {
        super(task);
        divideCommand = task.split(" ");
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
        String taskRepresent = getName() + " (" + divideCommand[3].substring(1)
                + ": " + getDateFormat() + ")";
        if (this.isDone()) {
            return "[D][X] " + taskRepresent;
        } else {
            return "[D][-] " + taskRepresent;
        }
    }
}
