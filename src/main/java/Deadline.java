import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }


    public Deadline(String name, LocalDate by, Boolean isDone) {
        super(name, isDone);
        this.by = by;
    }


    public String processDate(LocalDate originalDate) {
//        LocalDate date = LocalDate.parse(originalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String month = originalDate.getMonth().toString();
        String threeLetteredMonth = month.substring(0,1) + month.substring(1,3).toLowerCase();
        String day = Integer.toString(originalDate.getDayOfMonth());
        String year = Integer.toString(originalDate.getYear());

        String result = threeLetteredMonth + " " + day + " " + year;

        return result;
    }
                    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + processDate(by) + ")";
    }
}
