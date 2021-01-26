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

    @Override
    public String toString() {
        return  "[D]" + super.toString() +  "(by:" + dueDate + " " + this.dueTime.toString() +")" ;
    }
}
