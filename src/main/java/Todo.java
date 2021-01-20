package main.java;

public class Todo extends Task {
    Todo(String name) {
        super(name, "Make sure you do this task!");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
