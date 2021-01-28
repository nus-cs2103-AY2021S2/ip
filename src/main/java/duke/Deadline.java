package duke;

import duke.Task;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Deadline extends Task{
    private String deadlineDateString;
    private LocalDate deadlineDate;

    Deadline(String deadlineDateString, String deadlineDetail){
        super(deadlineDetail);
        try{
            this.deadlineDate = Task.dateStringToDate(deadlineDateString);
            this.deadlineDateString = deadlineDate.getMonth().toString().toLowerCase() + " " +
                    deadlineDate.getDayOfMonth() + " " + deadlineDate.getYear();
        }
        catch(DateTimeException e){
            System.err.println("Please input date in yyyy-MM-dd format.");
        }
    }
    public String getDeadline(){
        return this.deadlineDateString;
    }

    public LocalDate getDeadlineDate(){
        return this.deadlineDate;
    }

    @Override
    public String toString(){
        return "[D] | " + super.toString() + " | by: " + this.deadlineDateString;
    }
}
