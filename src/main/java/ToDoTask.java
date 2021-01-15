public class ToDoTask extends Task{
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
