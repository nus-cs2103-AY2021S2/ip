package duke.main;

/**
 * Inherited from Task, used to store information related to tasks of type 'todo'.
 *
 * Todos are tasks without any date/time attached to it.
 */
public class Todo extends Task{

    /**
     * Constructor for todo class object
     * @param description todo description
     */
    public Todo(String description) {
        super(description);
    }


    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String infoToStore() {
        String divider = " | ";
        return "T" +  divider
                + (isDone ? "1" : "0") + divider
                + description;
    }
}
