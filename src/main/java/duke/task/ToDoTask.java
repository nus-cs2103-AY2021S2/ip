package duke.task;

/**
 *  ToDo duke.tasks.Task.
 *
 *  @author Yap Jing Kang
 */

public class ToDoTask extends Task {

    /**
     *  duke.tasks.ToDoTask constructor.
     *
     *  @param name Name of duke.tasks.ToDoTask.
     */
    public ToDoTask(String name) {
        super(name);
    }

    public ToDoTask (String name, boolean isCompleted) {
        super(name);
        if (isCompleted) {
            this.markAsDone();
        }
    }

    public String toFileFormat() {
        return String.format("%s|%s|%s",
                "T",
                done == Status.DONE ? "1" : "0",
                name);
    }

    @Override
    public String toString() {
        if (done == Status.DONE) {
            return "[T][X] " + name;
        }
        return "[T][ ] " + name;
    }
}
