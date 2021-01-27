import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class ParseDateTime {
    // LIST OF SUPPORTED DATES
    // dd-MM ha i.e. "30-04 6PM"

    public static LocalDateTime parse(String s) {
        // format YYYY-MM-DD
        // todo exceptions/error
        // todo check length and see which format to use
        return parseFormat1(s);
    }

    // where s is formatted like "30-04 6PM"
    private static LocalDateTime parseFormat1(String s) {
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd-MM ha yyyy");
        int yyyy2 = Year.now().getValue();
        LocalDateTime d2 = LocalDateTime.parse(s + " " + yyyy2, f2);
        return d2;
    }

    // todo use this in your task toString
    public static String readableString(LocalDateTime d) {
        String patt = "dd MMM ha yyyy";
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern(patt);
        String str = f2.format(d);
        return str.substring(0, patt.length() - 4);
    }


    // testing purposes
    public static void main(String[] args) {
        LocalDate d3 = LocalDate.parse("2019-12-02");
        System.out.println(d3);

        // formatting
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String test = "08-12";
        int yyyy = Year.now().getValue();
        LocalDate d = LocalDate.parse(test + "-" + yyyy, f);
        System.out.println(d);
        System.out.println(Year.now().getValue());

        // parser for dd-MM ha
        // time currently is hour only, and PM needs to be in caps
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd-MM ha yyyy");
        String test2 = "30-04 6PM";
        int yyyy2 = Year.now().getValue();
        LocalDateTime d2 = LocalDateTime.parse(test2 + " " + yyyy2, f2);
        System.out.println(d2);
    }
}
