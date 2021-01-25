import java.time.LocalDate;

public class DeadlineTask extends Task {

    private LocalDate deadline;
    private String time;

    public DeadlineTask(String description, int id, int status, LocalDate deadline, String time) {
        super(description, id);
        super.isDone = status > 0;
        this.deadline = deadline;
        this.time = time;
    }

    public String serializeDeadline() {
        return deadline.toString() + " | " + time;
    }
    
    /** 
     * @return String
     */
    @Override public String toString() {
        return "[D]" + super.checkBoxToString() + description + " (by: "
                + deadline.getDayOfMonth() + " " + deadline.getMonth()
                + " " + deadline.getYear() + " " + time + "HRS)";
    }
}
