package duke.task;

import duke.DukeException;
import duke.data.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public LocalDateTime date;

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
        isDone = false;
    }

    public static Task createDeadline(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException("There's not enough information about your Deadline order!");
        }
        String[] args = command[1].split(" /by ", 2);
        if (args.length == 1 || args[0].isEmpty() || args[1].isEmpty()) {
            throw new DukeException("Looks like your Deadline order isn't complete...");
        }
        return new Deadline(args[0], TaskList.convertStringToDate(args[1]));
    }

    @Override
    public String getDate() {
        return " (by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")) + ")";
    }

    public String getFormattedString() {
        return "DEADLINE"
                + Data.splitter
                + (isDone ? "1" : "0")
                + Data.splitter
                + description
                + Data.splitter
                + date + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getDate() + "\n";
    }
}
