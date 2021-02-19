package duke.task;

/**
 * Represents a Todo.
 * Sub-class of Task.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public class Todo extends Task {
    /**
     * Returns a Todo.
     *
     * @param msg description of Todo.
     * @return Todo
     */
    public Todo(String msg) {
        super(msg);
    }

    /**
     * Returns a Todo.
     *
     * @param msg description of Todo.
     * @param isDone boolean that tracks whether Todo is completed.
     * @return Todo
     */
    Todo(String msg, Boolean isDone) {
        super(msg, isDone);
    }

    /**
     * Returns a Todo object that set boolean isDone as true.
     *
     * @return Todo Marks Todo as done.
     */
    @Override
    public Todo setDone() {
        System.out.println("Todo set done");
        return new Todo(this.msg, true);
    }

    /**
     * Encode format for save to storage.
     *
     * @return
     */
    @Override
    public String encode() {
        return "T" + "|" + super.encode();
    }
    /**
     * Compare Todo.
     *
     * @param task
     * @return
     */
    @Override
    public boolean equals(Task task) {
        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            boolean a = this.isDone.equals(todo.getDone());
            boolean b = this.msg.equals(todo.getMsg());
            return a && b;
        } else {
            return false;
        }
    }
    /**
     * Returns a String that describes Todo.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
