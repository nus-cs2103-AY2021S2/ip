public class ArgumentException extends DukeException {

    static final String MESSAGE = "Wrong Arguments Specified: ";

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
     * @param type Error type according to the list below:
     *             <p> 1. Format error of a todo-task </p>
     *             <p> 2. Format error of a deadline-task </p>
     *             <p> 3. Format error of a event-task </p>
     *             <p> 4. Format error of a done or delete command </p>
     */
    public ArgumentException(int type) {
        super(MESSAGE);
        this.type = type;
    }

    /**
     * Converts this object to a string that represents the error message
     * @return A string representing the error message depending on the specific error it is associated with
     */
    @Override
    public String toString() {
        if (this.type == 1) {
            return super.toString()
                    + "A todo-task should be specified as follows \n "
                    + "todo <task_description> \n "
                    + "-----------------------------------------------------";
        } else if (this.type == 2) {
            return super.toString()
                    + "A deadline-task should be specified as follows \n "
                    + "deadline <task_description> /by <task_deadline> \n "
                    + "-----------------------------------------------------";
        } else if (this.type == 3) {
            return super.toString()
                    + "A event-task should be specified as follows \n "
                    + "event <event_description> /at <event_date> \n "
                    + "-----------------------------------------------------";
        } else {
            return super.toString()
                    + "Please enter a valid item number in the list \n "
                    + "-----------------------------------------------------";
        }
    }
}
