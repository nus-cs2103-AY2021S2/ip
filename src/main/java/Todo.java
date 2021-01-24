package main.java;

public class Todo extends Task {
    Todo(String name) {
        super(name, "Make sure you do this task!");
    }

    Todo(String name, Boolean status) {
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
