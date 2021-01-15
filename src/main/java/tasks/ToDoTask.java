package tasks;

/**
 *  ToDo tasks.Task.
 *
 *  @author Yap Jing Kang
 */

public class ToDoTask extends Task {

    /**
     *  tasks.ToDoTask constructor.
     *
     *  @param name Name of tasks.ToDoTask.
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
