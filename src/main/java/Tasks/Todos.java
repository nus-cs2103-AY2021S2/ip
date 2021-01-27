package Tasks;

public class Todos extends DukeTask {

    public Todos(String name) {
        super(name, TaskType.TODO);
    }

    public Todos(String name, boolean isDone) {
        super(name, isDone, TaskType.TODO);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
