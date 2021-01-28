import java.time.LocalDate;

public class Deadline extends Task{
    LocalDate deadline;
    String time;

    public Deadline(String name, LocalDate deadline, String time) {
        super(name);
        this.deadline = deadline;
        this.time = time;
    }

    public Deadline(String name, boolean done, LocalDate deadline, String time) {
        super(name, done);
        this.deadline = deadline;
        this.time = time;
    }

    @Override
    public String toString() {
        String head = "[D][ ] ";
        if (this.done) {
            head = "[D][X] ";
        }
        return head + this.name + " (by: " + this.deadline.getMonth() + " "
                + this.deadline.getDayOfMonth() + " " + this.deadline.getYear() + " "
                + this.time + ")";
    }
}
