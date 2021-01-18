public class ArgumentException extends DukeException {

    static final String MESSAGE = "Wrong Arguments Specified: ";
    protected int type;

    public ArgumentException(int type) {
        super(MESSAGE);
        this.type = type;
    }

    @Override
    public String toString() {
        if (this.type == 1) {
            return super.toString() + "A todo-task should be specified as follows \n todo <task_description> \n -----------------------------------------------------";
        } else if (this.type == 2) {
            return super.toString() + "A deadline-task should be specified as follows \n deadline <task_description> /by <task_deadline> \n -----------------------------------------------------";
        } else if (this.type == 3) {
            return super.toString() + "A meeting-task should be specified as follows \n event <event_description> /at <event_date> \n -----------------------------------------------------";
        } else {
            return super.toString() + "Please enter a valid item number in the list \n -----------------------------------------------------";
        }
    }
}
