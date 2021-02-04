package models;

public class Todo {
    protected String message;
    protected boolean isDone = false;

    /**
     * Create a new Todo without specifying isDone attribute, which defaults to false
     *
     * @param message denoting Todo message
     */
    public Todo(String message) {
        this.message = message;
    }

    /**
     * Constructor to allow setting of isDone boolean
     *
     * @param message String message that a Todo contains
     * @param isDone boolean denoting if Todo is done
     */
    public Todo(String message, boolean isDone) {
        this.message = message;
        this.isDone = isDone;
    }

    /**
     * Render full message required to denote what each Todo is
     *
     * @return Type of todo, icon denoting whether it's done and message
     */
    public String getMessage() {
        return String.format("[T][%s] %s", this.getIsDoneIcon(), this.message);
    }

    /**
     * Protected method that returns an tick icon if the Todo is completed, else returns a X icon
     *
     * @return String unicode for the icon to render
     */
    protected String getIsDoneIcon() {
        return this.isDone ? "\u2713" : "\u2718";
    }

    /**
     * Getter method for getting the isDone attribute of a Todo
     *
     * @return boolean on whether a todo is done
     */
    public boolean isTodoDone() {
        return this.isDone;
    }

    /**
     * Getter method for getting raw message without formatting of a Todo
     *
     * @return String containing raw message of Todo
     */
    public String getRawMessage() {
        return this.message;
    }

    /**
     * Marks a Todo as done by returning a new Todo
     *
     * @return Todo that is marked as done
     */
    public Todo markAsDone() {
        return new Todo(this.message, true);
    }
}
