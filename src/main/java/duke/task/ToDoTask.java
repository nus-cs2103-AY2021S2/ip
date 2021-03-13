package duke.task;

/**
 *  ToDo Task.
 *
 *  @author Yap Jing Kang
 */

public class ToDoTask extends Task {

    /**
     *  ToDoTask constructor.
     *
     *  @param name Name of duke.tasks.ToDoTask.
     */
    public ToDoTask(String name) {
        super(name);
    }

    /**
     *  ToDoTask constructor. For use with persistent storage.
     *
     *  @param name Name of ToDoTask.
     *  @param isCompleted Whether ToDoTask is completed, or not.
     */
    public ToDoTask (String name, boolean isCompleted) {
        super(name);
        if (isCompleted) {
            this.markAsDone();
        }
    }

    /**
     *  Converts ToDoTask object to String suitable for storage.
     *
     *  @return String ToDoTask information in a file-friendly format
     */
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
