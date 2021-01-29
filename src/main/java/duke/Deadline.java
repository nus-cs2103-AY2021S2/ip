package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;
    private String description;

    public Deadline(LocalDateTime date, String description) {
        super(description + " " + date);
        this.date = date;
        this.description = description;
    }

    /**
     * Returns a String that has been formatted which contains the information of the Deadline task.
     * String is formatted into a form to be written into the file writer.
     *
     * @return String to be passed into file writer.
     */
    @Override
    public String formatToSave() {
        String str = "D | ";
        if (isDone) {
            str += "X |";
        } else {
            str += "O |";
        }
        str += " " + description + " | by: " + date.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"));
        return str;
    }

    /**
     * Returns a String that has been formatted which contains the information of the Deadline task.
     * String is formatted into a form to be printed by the Ui.
     *
     * @return String to be printed by Ui.
     */
    @Override
    public String toString() {
        String str = "[D]";
        if (isDone) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        str += " " + description + " (by: " + date.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm")) + ")";
        return str;
    }
}