package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDate atDate;
    private String time;

//    public EventTask(String description, boolean isDone, String at) {
//        super(description);
//        super.isDone = false;
//        this.at = at;
//        this.atDate = null;
//    }
    public EventTask(String description, boolean isDone, LocalDate atDate, String time) {
        super(description);
        super.isDone = false;
        this.atDate = atDate;
        this.time = time;
    }
    @Override
    public String toString() {
        if (this.atDate != null) {
            return "[E] " + super.toString() + " (by: "
                    + this.atDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + ", time: " + time + ")";
        }
        //error prone line below
        return "[E] " + super.toString() + " (by: " + this.atDate + ")";
    }
    @Override
    public String getTaskDetails() {
        String divider = " | ";
        return "E" +  divider
                + (isDone ? "1" : "0") + divider
                + description + divider
                + atDate + divider + time;
    }
}
