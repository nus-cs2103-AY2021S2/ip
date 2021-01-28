import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }
    @Override
    LocalDate getTime(){
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}