package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.TaskException;

public class Deadline extends Task {
    private final DateTimeFormatter FORMAT_ONE = DateTimeFormatter.ofPattern("d-M-uuuu");
    private final DateTimeFormatter FORMAT_TWO = DateTimeFormatter.ofPattern("uuuu-M-d");
    private String deadlineBy;
    private LocalDate dateBy;

    /**
     * Default constructor for deadline
     * @param description description of deadline
     * @param by String of when the deadline is by
     * @throws TaskException throws an error if date formatting is wrong
     */
    public Deadline(String description, String by) throws TaskException {
        super(description);
        this.deadlineBy = by;

        try {
            String trimmed = deadlineBy.trim();
            if (trimmed.substring(0, 4).contains("-")) {
                dateBy = LocalDate.parse(deadlineBy.trim(), FORMAT_ONE);
            } else {
                dateBy = LocalDate.parse(deadlineBy.trim(), FORMAT_TWO);
            }
        } catch (Exception e) {
            throw new TaskException("deadline must be of the format date-month-year, in numbers.");
        }
        /*
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
        dateBy = LocalDateTime.parse(deadlineBy, format);
        System.out.println(dateBy.format(DateTimeFormatter.ofPattern("MMM d uuuu HH:mm")));
        */
    }

    /**
     * Alternative constructor for deadline
     * @param description description of deadline
     * @param by String of when the deadline is by
     * @param doneInt index to signify if deadline is done or not
     * @throws TaskException throws an error if date formatting is wrong
     */
    public Deadline(String description, String by, int doneInt) throws TaskException {
        super(description, doneInt);
        this.deadlineBy = by;

        try {
            String trimmed = deadlineBy.trim();
            if (trimmed.substring(0, 4).contains("-")) {
                dateBy = LocalDate.parse(deadlineBy.trim(), FORMAT_ONE);
            } else {
                dateBy = LocalDate.parse(deadlineBy.trim(), FORMAT_TWO);
            }
        } catch (Exception e) {
            throw new TaskException("deadline must be of the format date-month-year, in numbers.");
        }
    }

    @Override
    public String toString() {
        String formattedDate = dateBy.format(DateTimeFormatter.ofPattern("MMM d uuuu"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D|" + super.toSaveFormat() + "|" + deadlineBy;
    }
}
