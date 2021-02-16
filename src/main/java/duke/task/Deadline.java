package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exceptions.TaskException;

/**
 * Deadline task class
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMAT_ONE = DateTimeFormatter.ofPattern("d-M-uuuu");
    private static final DateTimeFormatter FORMAT_TWO = DateTimeFormatter.ofPattern("uuuu-M-d");
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
            this.dateBy = getDateBy(by);
        } catch (TaskException e) {
            throw e;
        }
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

    private LocalDate getDateBy(String by) throws TaskException {
        try {
            String trimmed = by.trim();
            if (trimmed.substring(0, 4).contains("-")) {
                return LocalDate.parse(trimmed, FORMAT_ONE);
            } else {
                return LocalDate.parse(trimmed, FORMAT_TWO);
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

    @Override
    public void changeDescription(ArrayList<String> arrOfDescriptionToChange) throws TaskException {
        try {
            for (int i = 0; i < arrOfDescriptionToChange.size(); i = i + 2) {
                if (arrOfDescriptionToChange.get(i).equals("/de")) {
                    this.description = arrOfDescriptionToChange.get(i + 1);
                } else if (arrOfDescriptionToChange.get(i).equals("/by")) {
                    this.deadlineBy = arrOfDescriptionToChange.get(i + 1);
                    this.dateBy = getDateBy(arrOfDescriptionToChange.get(i + 1));
                } else {
                    throw new TaskException("Illegal tag detected! \nPlease tag the part of the task you wish to change"
                            + " using /de for general description, /by for deadline by and /at for event at.");
                }
            }
        } catch (TaskException e) {
            throw e;
        }
    }
}
