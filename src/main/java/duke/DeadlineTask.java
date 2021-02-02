package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDate byDate;
    private String time;

    /**
     * Creates a DeadlineTask object.
     * @param description description of DeadlineTask
     * @param isDone true if deadlineTask is done
     * @param byDate deadline date
     * @param time time on deadline date
     */
    public DeadlineTask(String description, boolean isDone, LocalDate byDate, String time) {
        super(description);
        super.isDone = isDone;
        this.time = time;
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        if (this.byDate != null) {
            return "[D] " + super.toString() + " (by: "
                    + this.byDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + ", time: " + time + ")";
        }
        //error prone line below
        return "[D] " + super.toString() + " (by: " + this.byDate + " time: " + time + ")";
    }
    @Override
    public String getTaskDetails() {
        String divider = " | ";
        return "D" + divider
                + (isDone ? "1" : "0") + divider
                + description + divider
                + byDate + divider + time;
    }
}
