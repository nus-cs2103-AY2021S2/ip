package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    private LocalDate deadline;

    public DeadlineTask(String description, String deadline) {
        super(description, "[D]");
        this.deadline = LocalDate.parse(deadline);
    }

    public String getDeadline() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return "(by: " + this.deadline.format(format) + ")";
    }

    public String getUnformattedDeadline() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.deadline.format(format);
    }
    public LocalDate getDeadlineAsLocalDate() {
        return this.deadline;
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
