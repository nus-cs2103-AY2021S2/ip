package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

    private LocalDate dueDate;
    private LocalTime dueTime;


    public Deadline(String title, LocalDate dueBy, LocalTime time) {
        super(title);
        this.dueDate = dueBy;
        this.dueTime = time;
    }

    public Deadline(String title, Boolean isCompleted, LocalDate dueBy, LocalTime time) {
        super(title, isCompleted);
        this.dueDate = dueBy;
        this.dueTime = time;
    }

    @Override
    public String changeFormat(){
        return "D" + super.changeFormat() + "," + this.dueDate + "," + this.dueTime;
    }

    @Override
    public String toString() {
        return  "[D]" + super.toString() + " (by: " + this.dueDate + " " + this.dueTime + ")";
    }
}
