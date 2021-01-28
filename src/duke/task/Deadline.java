package duke.task;
import duke.exceptions.TaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import java.time.LocalDateTime;

public class Deadline extends Task {
    private final DateTimeFormatter formatone = DateTimeFormatter.ofPattern("d-M-uuuu");
    private final DateTimeFormatter formattwo = DateTimeFormatter.ofPattern("uuuu-M-d");
    private String deadlineBy;
    private LocalDate dateBy;
    public Deadline(String description, String by) throws TaskException {
        super(description);
        this.deadlineBy = by;

        try {
            String trimmed = deadlineBy.trim();
            if (trimmed.substring(0,4).contains("-")) {
                dateBy = LocalDate.parse(deadlineBy.trim(), formatone);
            }else{
                dateBy = LocalDate.parse(deadlineBy.trim(), formattwo);
            }
        }catch(Exception e){
            throw new TaskException("deadline must be of the format date-month-year, in numbers.");
        }
        /*
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
        dateBy = LocalDateTime.parse(deadlineBy, format);
        System.out.println(dateBy.format(DateTimeFormatter.ofPattern("MMM d uuuu HH:mm")));
        */
    }

    public Deadline(String description, String by, int doneInt) throws TaskException{
        super(description, doneInt);
        this.deadlineBy = by;

        try {
            String trimmed = deadlineBy.trim();
            if (trimmed.substring(0,4).contains("-")) {
                dateBy = LocalDate.parse(deadlineBy.trim(), formatone);
            }else{
                dateBy = LocalDate.parse(deadlineBy.trim(), formattwo);
            }
        }catch(Exception e){
            throw new TaskException("deadline must be of the format date-month-year, in numbers.");
        }
    }

    @Override
    public String toString() {
        String formattedDate = dateBy.format(DateTimeFormatter.ofPattern("MMM d uuuu"));
        return "[D]" +super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String toSaveFormat(){
        return "D|" + super.toSaveFormat() + "|" + deadlineBy;
    }
}
