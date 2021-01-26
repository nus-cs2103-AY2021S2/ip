public class Todo extends Task {

    Todo (String task) {
        super(task);
    }

    Todo (String task, boolean done) {
        super(task, done);
    }

    @Override
    public Task finishTask() {
        return new Todo(this.task, true);
    }

    @Override
    public String saveString() {
        return "T|" + super.saveString();
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
