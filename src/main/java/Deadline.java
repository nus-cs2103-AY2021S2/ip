import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String date;
    private LocalDateTime localDate;

    public Deadline(String input, String date) {
        super(input);
        LocalDateTime lDate = new ParseDates().parseString(date);
        this.localDate = lDate;
    }

    public Deadline(String input, String date, int done) {
        super(input);
        this.date = date;
        if (done == 1) {
            this.doTask();
        }
    }

    @Override
    public String taskSave() {
        return "D" + super.taskSave() + " | " + date;
    }

    @Override
<<<<<<< HEAD
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
=======
    public String toString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + localDate.format(dateTimeFormatter) + ")";
>>>>>>> branch-Level-8
    }
}
