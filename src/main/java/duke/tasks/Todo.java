package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;

public class Todo extends Task {
    public Todo(String description) {
        this(description, false);
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static Todo parse(String s) throws DukeExceptionIllegalArgument {
        if (s.equals("")) {
            throw new DukeExceptionIllegalArgument("The description of a todo cannot be empty.");
        }
        return new Todo(s);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return "T | " + ((isDone) ? 1 : 0) + " | " + description;
    }
}
