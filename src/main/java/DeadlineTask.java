/**
 * 
 */
public class DeadlineTask extends Task {

    private String deadline;

    public DeadlineTask(String description, int id) {
        super(description, id);
        this.deadline = description.split("/by")[1];
        this.description = description.split("/by")[0];
    }

    public DeadlineTask(String description, int id, int status, String deadline) {
        super(description, id);
        super.isDone = status > 0;
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }
    
    /** 
     * @return String
     */
    @Override public String toString() {
        return "[D]" + super.checkBoxToString() + description + "(by:" + deadline + ")";
    }
}
