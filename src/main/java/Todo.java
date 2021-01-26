public class Todo extends Task{
    protected char type;
    public Todo(String task) {
        super(task);
        this.type = 'T';
    }

    public Todo(String task, boolean isDone) {
        super(task, isDone);
        this.type = 'T';
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
