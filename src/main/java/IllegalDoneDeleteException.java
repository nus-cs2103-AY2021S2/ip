public class IllegalDoneDeleteException extends StringIndexOutOfBoundsException {
    protected String taskType;

    IllegalDoneDeleteException(String message) {
        super(message);
    }

    IllegalDoneDeleteException(String message, String taskType) {
        super(message);
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        if (taskType.equals("done")) {
            return "You forgot to enter the task to mark as done!\n" + (char) 9 + (char) 9 + "Please re-enter!";
        } else {
            return "You forgot to enter the task to delete!\n" + (char) 9 + (char) 9 + "Please re-enter!";
        }
    }
}
