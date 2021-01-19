public class Todo extends Task{

    Todo(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        if (this.isDone.equals(true)) {
            return "[T][X] " + msg;
        } else {
            return "[T][ ] " + msg;
        }
    }
}
