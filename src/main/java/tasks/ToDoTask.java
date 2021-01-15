/**
 *  ToDo Task.
 *
 *  @author Yap Jing Kang
 */

public class ToDoTask extends Task {

    /**
     *  ToDoTask constructor.
     *
     *  @param name Name of ToDoTask.
     */
    public ToDoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (done == Status.DONE) {
            return "[T][X] " + name;
        }
        return "[T][ ] " + name;
    }
}
