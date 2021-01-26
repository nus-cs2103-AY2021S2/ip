import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private final String preposition;
    private final LocalDate date;

    Event(String description, String prepositionAndTime) throws DukeException{
        super(description);
        String[] strings = splitPrepositionAndTime(prepositionAndTime);

        if(strings.length != 2) {
            throw new DukeException("Please provide a preposition and a date after '/'.");
        }

        try {
            preposition = strings[0];
            date = LocalDate.parse(strings[1]);
        } catch(DateTimeParseException e) {
            throw new DukeException("Date must be in the format yyyy-mm-dd.");
        }
    }

    @Override
    public String toString(){
        String dateStr = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E][%s] %s (%s %s)", getStatusIcon(), description, preposition, dateStr);
    }
}
