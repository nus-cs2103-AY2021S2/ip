public class Todo extends Task {

    protected String by;

    public Todo(String description, boolean isDone) {
        super(description, isDone);

    }


    public static Todo getTodo(String description) {
        return new Todo(description, false);
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
