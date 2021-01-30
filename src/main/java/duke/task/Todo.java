package duke.task;

public class Todo extends ListItem {
    public Todo(String task) {
        super(task);
    }

    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public ListItem markAsDone() {
        return new Todo(super.getTask(), true);
    }

    @Override
    public String toString() {
        return "[T]" + (super.getDone() == true ? "[X] " : "[ ] ") + super.getTask();
    }

    public String getDate() {
        return "";
    }
}
