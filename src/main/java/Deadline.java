import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String name;
    private LocalDate deadline;
    private boolean done;

    Deadline(String name, String deadline) {
        this.name = name;
        this.deadline = LocalDate.parse(deadline);
        this.done = false;
    }

    Deadline(String name, String deadline, boolean done) {
        this.name = name;
        this.deadline = LocalDate.parse(deadline);
        this.done = done;
    }

    Deadline(String name, LocalDate deadline, boolean done) {
        this.name = name;
        this.deadline = deadline;
        this.done = done;
    }

    @Override
    Task done() {
        return new Deadline(this.name, this.deadline.toString(), true);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Deadline) {
            Deadline dl = (Deadline) obj;
            return dl.name.equals(this.name) && (dl.deadline.equals(this.deadline)) && (dl.done == this.done);
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[D][X] %s (by: %s)", this.name, this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }
        return String.format("[D][ ] %s (by: %s)", this.name, this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
