package main.java.subfiles;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + name;
    }
}
