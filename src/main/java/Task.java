import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private boolean completed;
    private String name;
    private LocalDate date;

    public Task(String n) {
        this.completed = false;
        this.name = n;
        this.date = LocalDate.now(); // Default date will be the current time
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getName() {
        return name;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    protected String formatString(String input, String keyWord) {
        // Looks for inputs with the format yyyy-mm-dd also works with / or . as the
        // separator.
        String regex = "(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])";
        if (input.contains(keyWord)) {
            Matcher m = Pattern.compile(regex).matcher(input);
            if (m.find()) {
                LocalDate tempDate = LocalDate.parse(m.group(0));
                setDate(tempDate);
                input = input.replace(keyWord, "(" + keyWord.substring(1) + ":") + ")";
                input = input.replaceAll(regex, tempDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
            } else {
                input = input.replace(keyWord, "(" + keyWord.substring(1) + ":") + ")";
            }
        }
        return input;

    }

    public String toString() {
        if (!this.isCompleted()) {
            return String.format("[ ] %s", this.getName());
        } else {
            return String.format("[X] %s", this.getName());
        }
    }

}
