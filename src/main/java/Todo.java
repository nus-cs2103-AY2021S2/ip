public class Todo extends Task{
    Todo(String task) {
        super(task);
    }

    Todo(boolean done, String task) {
        super(task);
        this.done = done;
    }

    String fileString() {
        return "T | " + this.done + " | " + this.task;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
