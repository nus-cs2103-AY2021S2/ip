import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean done = false;

    public Task(String description) {
        this.description = description;
    }

    public void finished() {
        done = true;
    }

    protected String statusicon() {
        if (done) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    public String get_done() {
        if (done) {
            return "1";
        }
        return "0";
    }

    public String get_description() {
        return description;
    }

    public abstract String get_initial();
    public abstract LocalDate get_date();

    @Override
    public String toString() {
        return this.statusicon() + description;
    }
}
