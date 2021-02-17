import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 * @author Arihant Jain
 */
public class Deadlines extends Task {

    /**
     * The By.
     */
    private final String by;
    /**
     * The Date.
     */
    private LocalDate date;

    /**
     * Instantiates a new Deadlines.
     *
     * @param description the task description
     * @param by          the by
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
        setDate(this.by);
    }

    /**
     * Sets date.
     *
     * @param by the by String
     */
    public void setDate(String by) {
        String[] byArr = by.split("-");
        this.date = LocalDate.of(Integer.parseInt(byArr[0]),
                Integer.parseInt(byArr[1]),
                Integer.parseInt(byArr[2]));
    }

    /**
     * Get date string.
     *
     * @return the string
     */
    public String getDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return this.date.format(format);
    }

    /**
     * Get by string.
     *
     * @return the string
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Get description string.
     *
     * @return the string
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get LocalDate object.
     *
     * @return the LocalDate object.
     */
    public LocalDate getDateObj() {
        return this.date;
    }

    /***
     * Get list variation Deadline Task String.
     * @return String Deadline Task String
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.getDate() + ")";
    }
}
