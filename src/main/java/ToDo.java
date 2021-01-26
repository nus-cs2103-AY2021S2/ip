public class ToDo extends Task {

    public ToDo(String details) {
        super(details);
    }

    private ToDo(String details, boolean indicator) {
        super(details, indicator);
    }

    // overrides completeTask() method
    public ToDo completeTask() {
        return new ToDo(this.getTask_details(), true);
    }

    // overrides taskStatus() method
    public String taskStatus() {
        if (this.isDone()) {
            return "T 1 " + this.getTask_details();
        } else {
            return "T 0 " + this.getTask_details();
        }
    }
}
