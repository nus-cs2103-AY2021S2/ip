package ekud.task;

import java.util.LinkedList;

/**
 * A simple ToDo task with no added features
 */
public class ToDo extends Task {
    /**
     * Construct a new ToDo Task with a given description.
     *
     * @param description The description of the new ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return String LinkedList containing {"T", isDone, description}.
     */
    @Override
    public LinkedList<String> export() {
        LinkedList<String> list = super.export();
        list.addFirst("T");
        return list;
    }
}
