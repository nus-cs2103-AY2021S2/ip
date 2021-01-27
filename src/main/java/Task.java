public class Task {
    protected String info;
    protected boolean isDone;
    protected taskType type;
    private String output;

    public Task(String info, taskType type) {
        this.info = info;
        this.isDone = false;
        this.type = type;
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
        System.out.println("Well done, Master! I've marked this task as done:");
        return toString();
    }

    public taskType getType() {
            return this.type;
    }

    @Override
    public String toString() {
        output = "[" + checkIcon() + "]" + this.info;
        return output;
    }

}
