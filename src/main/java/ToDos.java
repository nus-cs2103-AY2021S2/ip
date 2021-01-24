public class ToDos extends DukeTask {

    public ToDos(String name) {
        super(name, TaskType.TODO);
    }

    public ToDos(String name, boolean isDone) {
        super(name, isDone, TaskType.TODO);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
