public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public Task setDone() {
        Todo doneTask = new Todo(this.name);
        doneTask.isDone = true;
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
