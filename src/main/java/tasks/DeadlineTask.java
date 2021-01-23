package tasks;

public class DeadlineTask extends Task{
    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description, "[D]");
        this.deadline = deadline;
    }

    public String getDeadline() {
        int index = deadline.indexOf(" ");
        return "(" + this.deadline.substring(0, index) + ":" + this.deadline.substring(index) + ")";
    }

    @Override
    public void getStatusAndTask() {
        System.out.println("      " + this.type + this.getStatus() + this.description + this.getDeadline());
    }

    @Override
    public String toString() {
        return "       " + this.type + super.toString().trim() + " " + this.getDeadline();
    }
}
