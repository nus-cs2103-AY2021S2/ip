package duke.task;

public class Todo extends Task{

    public Todo(String description){
        super(description);
    }

    @Override
    String toFileString() {
        return String.format("todo %s", description);
    }

    @Override
    public String toString(){
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
