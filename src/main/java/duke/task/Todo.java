package duke.task;

public class Todo extends Task{
    public Todo(String task) {
        super(task);
    }

    public Todo(boolean done, String task) {
        super(task);
        this.done = done;
    }

    public String fileString() {
        return "T | " + this.done + " | " + this.task;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
