package main.java.entity;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }


    @Override
    public String toString() {
        return "[T][" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    @Override
    public String toFileString() {
        return "T|" + (this.isDone ? "1" : "0") + "|" + this.name;
    }
}
