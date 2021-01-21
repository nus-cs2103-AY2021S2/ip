public class IllegalDoneDeleteException extends StringIndexOutOfBoundsException {
    String taskType;

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
            return "You forgot to enter the task to mark as done! Please re-enter!";
        } else {
            return "You forgot to enter the task to delete! Please re-enter!";
        }
    }
}
