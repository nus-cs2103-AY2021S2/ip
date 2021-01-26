import java.time.LocalDate;
import java.time.Month;

public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.of(2020, 1, 25);
    }
    @Override
    public String getInitial() {
        return "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
