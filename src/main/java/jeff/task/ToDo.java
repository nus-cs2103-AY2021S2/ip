package jeff.task;

/**
 * Represents a ToDo, child class of Task.
 */
public class ToDo extends Task {

    /**
     * Constructor for Event.
     *
     * @param name Name of Event.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns symbol representing ToDo (i.e. "T").
     *
     * @return "T".
     */
    public String getSymbol() {
        return "T";
    }
}
