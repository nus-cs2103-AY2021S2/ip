package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;

    public Event(LocalDateTime startDate, LocalDateTime endDate, String description) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    /**
     * Returns a String that has been formatted which contains the information of the Event task.
     * String is formatted into a form to be written into the file writer.
     *
     * @return String to be passed into file writer.
     */
    @Override
    public String formatToSave() {
        String str = "E | ";
        if (isDone) {
            str += "X |";
        } else {
            str += "O |";
        }
        str += " " + description + " | from: " + startDate.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"))
                + " | to: " + endDate.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"));
        return str;
    }

    /**
     * Returns a String that has been formatted which contains the information of the Event task.
     * String is formatted into a form to be printed by the Ui.
     *
     * @return String to be printed by Ui.
     */
    @Override
    public String toString() {
        String str = "[E]";
        if (isDone) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        str += " " + description + " (from: " + startDate.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"))
                + " - " + endDate.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm")) + ")";
        return str;
    }
}