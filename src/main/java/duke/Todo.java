package main.java.duke;

public class Todo extends Task {
    protected static final String TAG = "[T]";

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return Todo.TAG + super.toString();
    }
}
