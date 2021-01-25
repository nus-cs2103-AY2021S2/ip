import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeConverter {
    protected String[] inputSplit;

    public DateTimeConverter(String[] inputSplit) {
        this.inputSplit = inputSplit;
    }

    public LocalDate convertDate() {
        String date = inputSplit[1].substring(3);

        if (date.length() > 10) {
            date = date.substring(0, date.length() - 1);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formattedDate = LocalDate.parse(date, formatter);
        return formattedDate;
    }

    public LocalTime convertTime(String s) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h a");

        if (s.equals("from")) {
            String from = inputSplit[2].substring(5, inputSplit[2].length() - 1).toUpperCase();
            LocalTime formattedFrom = LocalTime.parse(from, timeFormatter);
            return formattedFrom;
        } else {
            String to = inputSplit[3].substring(3).toUpperCase();
            LocalTime formattedTo = LocalTime.parse(to, timeFormatter);
            return formattedTo;

        }
    }
}
