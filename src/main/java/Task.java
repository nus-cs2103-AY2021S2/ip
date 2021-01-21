public class Task {
    protected String info;
    protected boolean isDone;
    //protected int taskType;
    private String output;

    public Task(String info) {
        this.info = info;
        //this.taskType = taskType;
        this.isDone = false;
    }

    public String getInfo() {
        return this.info;
    }

    public String checkIcon() {
        if (isDone) {
            return "\u0058";
        } else {
            return " ";
        }
    }

    public String markCompleted() {
        isDone = true;
        System.out.println("Well done, keep it up! I've marked this task as done:");
        return toString();
    }

    @Override
    public String toString() {
        output = "[" + checkIcon() + "] " + info;
        return output;
    }

}
