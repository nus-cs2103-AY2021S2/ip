import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate deadline;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-M-dd");


    Deadline(String name, String time) {
        super(name);
        this.deadline = LocalDate.parse(time, format);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd YYYY")) + ")";
    }
}
