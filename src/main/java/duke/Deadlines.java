<<<<<<< HEAD:src/main/java/duke/Deadlines.java
package duke;
=======
import java.time.LocalDate;
import java.time.LocalTime;
>>>>>>> branch-Level-8:src/main/java/Deadlines.java

public class Deadlines extends Task {

    private LocalDate dueDate;
    private LocalTime dueTime;


    public Deadlines(String title, LocalDate dueBy, LocalTime time) {
        super(title);
        this.dueDate = dueBy;
        this.dueTime = time;
    }

    public Deadlines(String title, Boolean b, String dueBy) {
        super(title, b);
        this.dueDate = dueBy;
    }

    @Override
    public String changeFormat(){
        return "D" + super.changeFormat() + ":" + this.dueDate;
    }

    @Override
    public String toString() {
        return  "[D]" + super.toString() +  "(by:" + dueDate + " " + this.dueTime.toString() +")" ;
    }
}
