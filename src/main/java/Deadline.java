import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task{
    private final String preposition;
    private final LocalDate date;

    Deadline(String description, String preposition, LocalDate date) {
        super(description);
        this.preposition = preposition;
        this.date = date;
    }

    @Override
    String toFileString() {
        return String.format("event %s | %s %s", description,
                preposition, date.format(DateTimeFormatter.ofPattern("yyyy-mm-dd")));
    }

    @Override
    public String toString(){
        String dateStr = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D][%s] %s (%s %s)", getStatusIcon(), description, preposition, dateStr);
    }
}
