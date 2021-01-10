package models;

public class Deadline extends Todo {
    /** String denoting the deadline of a Deadline object */
    protected String deadline;

    /**
     * Constructor to allow setting of the deadline in a Deadline object
     * 
     * @param message   String message that a Todo contains
     * @param eventTime String specific to deadlines denoting when the deadline is
     *                  due
     */
    public Deadline(String message, String deadline) {
        super(message);
        this.deadline = deadline;
    }

    /**
     * Constructor to allow setting of the isDone attribute of an Deadline
     * 
     * @param message  String message that a Todo contains
     * @param isDone   boolean denoting if a Todo is done
     * @param deadline String specific to deadlines denoting the deadline of a
     *                 Deadline Todo
     */
    public Deadline(String message, boolean isDone, String deadline) {
        super(message, isDone);
        this.deadline = deadline;
    }

    @Override
    /**
     * Method overriden from Todo's getMessage method to return Deadline type and
     * deadline
     * 
     * @return String to be rendered to give information on the Deadline
     */
    public String getMessage() {
        return String.format("[D][%s] %s (by:%s)", this.getIsDoneIcon(), this.message, this.deadline);
    }

    @Override
    /**
     * Method overridden the super class' to return a new Deadline that is marked as
     * done instead of a new Todo
     * 
     * @return Deadline that is marked as done
     */
    public Deadline markAsDone() {
        return new Deadline(this.message, true, this.deadline);
    }
}
