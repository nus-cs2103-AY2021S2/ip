package data.task;

public class Todo extends Task {


    public Todo(String description, boolean isDone) {
        super(description, isDone);

    }

    public static Todo getTodo(String description) {
        return new Todo(description, false);
    }

    @Override
    public String getContentString(){
        return String.format("T|%d|%S|%S",isDone ? 1 : 0, this.description);

    }

    @Override
    public Task markDone(){
        return new Todo(description,true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
