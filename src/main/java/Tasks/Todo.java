package Tasks;

public class Todo extends DukeTask {

    public Todo(String name) {
        super(name, TaskType.TODO);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone, TaskType.TODO);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
