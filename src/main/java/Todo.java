public class Todo extends Task{

    Todo(String msg) {
        super(msg);
    }

    Todo(String msg, Boolean isDone) {
        super(msg, isDone);
    }

    @Override
    public Todo setDone() {
        return new Todo(this.msg, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
