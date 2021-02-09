package duke.task;

import duke.exception.EmptyArgumentException;

public class ToDos extends Task{
    /**
     * Creates a To Do object that is essentially a wrapper on task.
     *
     * @param description Description of To Do
     * @throws EmptyArgumentException when Description is empty or whitespace
     */
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
