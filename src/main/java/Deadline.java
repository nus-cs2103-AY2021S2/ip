package main.java;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    final DateTimeFormatter format = DateTimeFormatter.ofPattern("d-M-uuuu");

    protected String deadlineBy;
    LocalDate dateBy;
    public Deadline(String description, String by) throws TaskException{
        super(description);
        this.deadlineBy = by;

        try {
            dateBy = LocalDate.parse(deadlineBy, format);
        }catch(Exception e){
            throw new TaskException("deadline must be of the format date-month-year, in numbers.");
        }
        /*
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
        dateBy = LocalDateTime.parse(deadlineBy, format);
        System.out.println(dateBy.format(DateTimeFormatter.ofPattern("MMM d uuuu HH:mm")));
        */
    }

    public Deadline(String description, String by, int doneInt){
        super(description, doneInt);
        this.deadlineBy = by;
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
