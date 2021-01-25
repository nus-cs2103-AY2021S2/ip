package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;

public class Deadlines extends Task {

    private LocalDate dueDate;
    private LocalTime dueTime;


    public Deadlines(String title, LocalDate dueBy, LocalTime time) {
        super(title);
        this.dueDate = dueBy;
        this.dueTime = time;
    }

    public Deadlines(String title, Boolean b, LocalDate dueBy, LocalTime time) {
        super(title, b);
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
