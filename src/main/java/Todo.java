package main.java;

public class Todo extends Task {
    protected static final String TAG = "[T]";

    Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return Todo.TAG + super.toString();
    }
}
