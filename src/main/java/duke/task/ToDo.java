package duke.task;

import java.util.ArrayList;

import duke.exceptions.TaskException;

/**
 * Todo task class
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, int doneInt) {
        super(description, doneInt);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T|" + super.toSaveFormat();
    }

    public void changeDescription(ArrayList<String> arrOfDescriptionToChange) throws TaskException {
        super.changeDescription(arrOfDescriptionToChange);
    }
}
