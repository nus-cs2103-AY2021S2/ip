public class Event extends Task {
    public String eTime;

    public Event(String taskName, String eTime) {
        super(taskName);
        this.eTime = eTime;
    }

    @Override
    public String printTask() {
        String ans;
        if (taskDone) {
            ans = "[E][X] " + this.taskName + " (at: " + this.eTime + ")";
        } else {
            ans = "[E][ ] " + this.taskName + " (at: " + this.eTime + ")";
        }
        return ans;
    }
}

