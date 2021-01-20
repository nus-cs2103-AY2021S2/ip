public class Todo extends Task{


    public Todo(String taskName) {
        super(taskName);

    }


    @Override
    public String toString() {
        return String.format("[T][%s] %d. %s", super.isDone(), super.getIndex(), super.getTaskName());
    }
}
