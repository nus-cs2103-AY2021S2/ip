package duke.task;

/**
 * Represents the Todo type task that does not have a due date
 */
public class Todo extends ListItem {
    /**
     * Constructor for Todo that was not provided the task done status
     * @param task takes in a string and pass to parent's constructor as task name
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * the overloaded constructor that allows taking the status of the task
     */
    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public ListItem markAsDone() {
        return new Todo(super.getTask(), true);
    }

    @Override
    public String toString() {
        return "[T]" + (super.getDone() == true ? "[X] " : "[ ] ") + super.getTask() + super.printTags();
    }

    /**
     * @return empty string since Todo does not has a due date
     */
    public String getDate() {
        return "";
    }
}
