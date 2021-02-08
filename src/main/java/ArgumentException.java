public class ArgumentException extends DukeException {

    static final String MESSAGE = "Wrong Arguments Specified: \n";

    /**
     * Error type according to the list below:
     * <p> 1. Format error of a todo-task </p>
     * <p> 2. Format error of a deadline-task </p>
     * <p> 3. Format error of a event-task </p>
     * <p> 4. Format error of a done or delete command </p>
     */
    protected int type;

    /**
     * Initializes a newly created ArgumentException object with the error type.
     *
     * @param type Error type according to the list below:
     *             <p> 1. Format error of a todo-task </p>
     *             <p> 2. Format error of a deadline-task </p>
     *             <p> 3. Format error of a event-task </p>
     *             <p> 4. Format error of a done or delete command </p>
     */
    public ArgumentException(int type) {
        super(MESSAGE);
        this.type = type;
        assert (type == 1 || type == 2 || type == 3 || type == 4): "Problem in Argument Exception type.";
    }

    /**
     * Converts this object to a string that represents the error message
     *
     * @return A string representing the error message depending on the specific error it is associated with
     */
    @Override
    public String toString() {
        switch (this.type) {
        case 1:
            return super.toString()
                + "A todo-task should be specified as follows \n "
                + "todo <task_description>";
        case 2:
            return super.toString()
                + "A deadline-task should be specified as follows \n "
                + "deadline <task_description> /by <task_deadline>";
        case 3:
            return super.toString()
                + "A event-task should be specified as follows \n "
                + "event <event_description> /at <event_date>";
        case 4:
            return super.toString()
                + "Please enter a valid item number in the list.";
        default:
            assert false: "Problem in Argument Exception type.";
            return "Problem in Argument Exception type.";
        }
    }
}
