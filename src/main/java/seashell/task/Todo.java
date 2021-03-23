package seashell.task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public Todo setDone() {
        Todo doneTask = new Todo(this.getName(), true);
        return doneTask;
    }

    @Override
    public String getSaveText() {
        return "T | " + super.getSaveText();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
