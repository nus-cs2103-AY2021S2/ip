package duke;

/**
 * The Todo class inherits from Task and
 * uses parent constructor.
 *
 * @author  Justin Gnoh
 * @version 1.0
 * @since   2021-02-06
 */
public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }
    public Todo(String name, Boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
