import java.time.LocalDate;

public class Event extends Task{
    LocalDate date;
    String time;

    public Event(String name, LocalDate date, String time) {
        super(name);
        this.date = date;
        this.time = time;
    }

    public Event(String name, boolean done, LocalDate date, String time) {
        super(name, done);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        String head = "[E][ ] ";
        if (this.done) {
            head = "[E][X] ";
        }
        return head + this.name + " (at: " + this.date.getMonth() + " "
                + this.date.getDayOfMonth() + " " + this.date.getYear() + " "
                + this.time + ")";
    }
}
