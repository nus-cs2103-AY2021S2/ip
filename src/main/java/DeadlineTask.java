import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DeadlineTask extends Task {

    private LocalDate deadline;
    private int time;

    public DeadlineTask(String description, int id, LocalDate deadline, int time) {
        super(description, id);
        this.deadline = deadline;
        this.time = time;
    }

    public DeadlineTask(String description, int id, int status, LocalDate deadline, int time) {
        super(description, id);
        super.isDone = status > 0;
        this.deadline = deadline;
        this.time = time;
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
        return "[D]" + super.checkBoxToString() + description + " (by: "
                + deadline.getMonth() + " " + deadline.getDayOfMonth()
                + " " + deadline.getYear() + " " + time + "HRS)";
    }
}
