import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDateTime dateTime;

    public Deadline(String desc, LocalDateTime dateTime) {
        super(desc);
        this.dateTime = dateTime;
    }

    @Override
    public String getDesc() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, EEE ha");
        return this.desc + " (by: " + this.dateTime.format(formatter) + ")";
    }

    @Override
    public String getTypeSymbol() {
        return "D";
    }
}
