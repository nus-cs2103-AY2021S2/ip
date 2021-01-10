package models;

public class Todo {
    String message;
    boolean isDone = false;

    public Todo(String message) {
        this.message = message;
    }

    public Todo(String message, boolean isDone) {
        this.message = message;
        this.isDone = isDone;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isTodoDone() {
        return this.isDone;
    }

    public Todo markAsDone() {
        return new Todo(this.message, true);
    }
}
