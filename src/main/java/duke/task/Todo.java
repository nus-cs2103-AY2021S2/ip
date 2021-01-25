package main.java.duke.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name, "Make sure you do this task!");
    }

    public Todo(String name, Boolean status) {
        this(name);
        this.status = status;
    }

    @Override
    public String toFileString() {
        return "T," + (this.status ? "1" : "0") + "," + this.name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
