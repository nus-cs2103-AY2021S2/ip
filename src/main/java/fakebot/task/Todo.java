package fakebot.task;

/**
 * ToDos Task Type
 */
public class Todo extends Task {
    /**
     * Class constructor specifying the task description.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
