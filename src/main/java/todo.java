import java.time.LocalDate;
import java.time.Month;

public class todo extends Task {
    todo(String description) {
        super(description);
    }

    @Override
    public LocalDate get_date() { return LocalDate.of(2020, 1, 25); }
    @Override
    public String get_initial() { return "T"; }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
