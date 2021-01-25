public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String printTask() {
        String ans;
        if (taskDone) {
            ans = "[T][X] " + this.taskName;
        } else {
            ans = "[T][ ] " + this.taskName;
        }
        return ans;
    }
}
