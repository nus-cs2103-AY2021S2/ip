import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    private final String by;
    Deadlines(String name, String by){
        super(name);
        this.by = by;
    }

    public LocalDateTime parse(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public String parseDate() {
        return this.parse(this.by).format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    @Override
    public String toString(){
        return "[D]" + (this.done ? "[X] " : "[ ]") + this.getTaskName() +  " (by: " +
                parseDate() + ")";
    }
}
