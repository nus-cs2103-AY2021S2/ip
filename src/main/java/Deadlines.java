import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private final String TYPE_ICON = "[D]";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    private LocalDateTime time;

    private String by;

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
        return String.format("%s(by:%s)", this.description, this.by);
    }

    @Override
    public String toString() {
        return String.format("%s(by:%s)", this.description, this.by);
    }

}
