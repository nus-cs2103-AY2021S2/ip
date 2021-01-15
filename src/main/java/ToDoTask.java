public class ToDoTask extends Task{
    public ToDoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (done) {
            return "[T][X] " + name;
        }
        return "[T][ ] " + name;
    }
}
