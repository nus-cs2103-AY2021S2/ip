package duke.task;

/** A simple task without any additional info */
public class ToDo extends Task {
    /**
     * Constructor for a ToDo task
     *
     * @param desc Description of the task
     */
    public ToDo(String desc) {
        super(desc, false);
    }

    /**
     * Alternate constructor for a ToDo task whereby you can indicate it's completion
     *
     * @param desc Description of the task
     * @param isDone Completion state of the task
     */
    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    /**
     * Returns a letter symbol for the ToDo
     *
     * @return Letter symbol for the ToDo
     */
    @Override
    public String getTypeSymbol() {
        return "T";
    }

    /**
     * Returns the ToDo's details in a format to be saved into the hard disk
     *
     * @return ToDo's detail in a savable format
     */
    @Override
    public String toSaveInfoString() {
        return this.getTypeSymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.desc;
    }
}
