package duke.task;

import duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private boolean isDone;
    private String type;
    private String preposition;
    private LocalDate date;

    public Task(String type, String name) {
        this.name = name;
        this.isDone = false;
        this.type = type;
        this.date = null;
    }
    
    public Task(String type, String name, String date, String preposition) {
        this.name = name;
        this.isDone = false;
        this.type = type;
        this.date = LocalDate.parse(date);
        this.preposition = preposition;
    }

    public String getType() {
        return type;
    }

    public boolean getDone() {
        return isDone;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (type.equals("E") || type.equals("D")) {
            return String.format("[%s][%s] %s (%s: %s)",
                    type,
                    (isDone) ? "X" : " ",
                    name,
                    preposition,
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } else {
            return String.format("[%s][%s] %s",
                    type,
                    (isDone) ? "X" : " ",
                    name);
        }
    }

    public String toSaveFormat() {
        String line = type + " | " + (isDone ? "1" : "0") + " | " + name;
        if (type.equals("E") || type.equals("D")) {
            line += " | " + date + " | " + preposition;
        }
        return line;
    }

    public static Task createTask(String taskName, String type, String date, String preposition) throws DukeException {
        if (taskName.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        }
        if (type.equals("todo")) {
            return new Task("T", taskName);
        } else if (type.equals("event")) {
            return new Task("E", taskName, date, preposition);
        } else if (type.equals("deadline")) {
            return new Task("D", taskName, date, preposition);
        } else {
            throw new DukeException("☹ OOPS!!! Tried to add wrong task type!");
        }
    }
}
