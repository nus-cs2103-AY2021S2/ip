package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{
    private LocalDate timing;

    public EventTask(String description, String timing) {
        super(description, "[E]");
        this.timing = LocalDate.parse(timing);
    }

    public String getTiming() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return "(at: " + this.timing.format(format) + ")";
    }

    public String getUnformattedTiming() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.timing.format(format);
    }

    public LocalDate getTimingAsLocalDate() {
        return this.timing;
    }

    public char getType() {
        return type.charAt(1);
    }

    @Override
    public void getStatusAndTask() {
        System.out.println("      " + this.type + this.getStatus() + this.description + this.getTiming());
    }

    @Override
    public String toString() {

        return "       " + this.type + super.toString().trim() + " " + getTiming();
    }
}
