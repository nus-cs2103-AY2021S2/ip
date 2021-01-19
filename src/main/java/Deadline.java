public class Deadline extends Task{
    public String dlTime;

    public Deadline(String taskName, String dlTime) {
        super(taskName);
        this.dlTime = dlTime;
    }

    @Override
    public String printTask() {
        String ans;
        if (taskDone) {
            ans = "[D][X] " + this.taskName + " (by: " + this.dlTime + ")";
        } else {
            ans = "[D][ ] " + this.taskName + " (by: " + this.dlTime + ")";
        }
        return ans;
    }
}
