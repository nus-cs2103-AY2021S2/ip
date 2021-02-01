package duke;

import duke.Task;

public class ToDo extends Task {
    ToDo(String toDoDetail) {
        super(toDoDetail);
    }

    @Override
    public String toString() {
        return "[T]" + " | " + super.toString();
    }
}
