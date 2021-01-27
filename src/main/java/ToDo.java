public class ToDo extends Task{
    protected char type;
    public ToDo(String task) {
        super(task);
        this.type = 'T';
    }

    public ToDo(String task, boolean isDone) {
        super(task, isDone);
        this.type = 'T';
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    @Override
    public String getData() {
        return "[" + type + "]" + super.toString();
    }
}
