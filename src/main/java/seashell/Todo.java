package seashell;

public class Todo extends Task {

    protected Todo(String name) {
        super(name);
    }

    @Override
    public Todo setDone() {
        Todo doneTask = new Todo(this.getName());
        doneTask.setDone();
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
