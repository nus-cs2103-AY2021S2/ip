import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private final String TYPE_ICON = "[D]";
    private final String ICON = "D";
    private final String DELIMITER = "|";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    private LocalDateTime time;

    public Deadlines(String description, String by) {
        super(description);

        System.out.println(description);
        System.out.println(by);

        if (by == null) {
            time = null;
        } else {
            try {
                time = LocalDateTime.parse(by, formatter);
            } catch (Exception e) {
                time = null;
            }
        }
    }

    @Override
    public String getTypeIcon() {
        return this.TYPE_ICON;
    }

    @Override
    public String getDescription() {
        if (time == null) {
            return this.description;
        } else {
            return String.format("%s (by: %s)", this.description, this.time.format(formatter));
        }
    }

    @Override
    public String tokenize() {

        String isDoneString = isDone? "1" : "0";

        String result;

        if (time == null) {
            result = ICON + DELIMITER + isDoneString + DELIMITER + this.description;
        } else {
            result = ICON + DELIMITER + isDoneString + DELIMITER + this.description + DELIMITER + this.time.toString();
        }
        return result;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
