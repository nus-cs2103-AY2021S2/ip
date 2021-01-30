package fakebot.task;
/**
 * ToDos Task Type
 */
public class ToDos extends Task {
    /**
     * Class constructor specifying the task description.
     */
    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
