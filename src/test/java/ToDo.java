public class ToDo extends Task{

    ToDo(String s, boolean b) {
        super(s, b);
    }

    ToDo setAsDone() {
        return new ToDo(this.info, true);
    }

    ToDo setAsUndone() {
        return new ToDo(this.info, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Task {
    String info;
    boolean isDone;

    Task(String s, boolean b) {
        this.info = s;
        this.isDone = b;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    Task setAsDone() {
        return new Task(this.info, true);
    }

    Task setAsUndone() {
        return new Task(this.info, false);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.info;
    }
}