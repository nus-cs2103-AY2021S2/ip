package duke.tasks;// Original definition from the webpage of course
// https://nus-cs2103-ay2021s2.github.io/website/schedule/week2/project.html
import duke.commands.DukeCommand;
import duke.exceptions.DukeExceptionIllegalArgument;

import java.time.format.DateTimeFormatter;

// TODO: Throw exception when description/by/at contains " | "
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this(description, false);
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    public void setDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713": "\u2718");
    }

    public static Task parseFileString(String s) throws DukeExceptionIllegalArgument {
        String[] args = s.split(" \\| ");
        boolean isDone = args[1].equals("1");

        Task task;
        switch (args[0]) {
        case "E":
            task = Event.parse(args[2] + " /at " + args[3]);
            break;
        case "T":
            task = Todo.parse(args[2]);
            break;
        case "D":
            task = Deadline.parse(args[2] + " /by " + args[3]);
            break;
        default:
            throw new DukeExceptionIllegalArgument("Incorrect task list data.");
        }
        if (isDone) {
            task.setDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public String toFileString() {
        return toString();
    }
}
