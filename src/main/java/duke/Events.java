<<<<<<< HEAD:src/main/java/duke/Events.java
package duke;
=======
import java.time.LocalDate;
import java.time.LocalTime;
>>>>>>> branch-Level-8:src/main/java/Events.java

public class Events extends Task {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

<<<<<<< HEAD:src/main/java/duke/Events.java
    public Events(String title, Boolean b, String data) {
        super(title, b);
        this.date = data;
    }

    public Events(String title, String date) {
=======
    public Events(String title, LocalDate date, LocalTime startTime, LocalTime endTime) {
>>>>>>> branch-Level-8:src/main/java/Events.java
        super(title);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String changeFormat(){
        return "E" + super.changeFormat() + ":" + this.date;
    }

    @Override
    public String toString() {
        return  "[E]" + super.toString() + "(at:" + this.date + " from " + this.startTime + " to"
                + " " + this.endTime + ")";
    }
}
