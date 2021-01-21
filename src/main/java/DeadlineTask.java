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

    @Override public String toString() {
        return "[D]" + super.checkBoxToString() + description + "(by:" + deadline + ")";
    }
}
