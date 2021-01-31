package duke.task;

import duke.exception.EmptyArgumentException;

public class ToDos extends Task{
    public ToDos(String description) throws EmptyArgumentException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T," + super.toBaseFileString();
    }
}
