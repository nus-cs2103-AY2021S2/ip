package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for containing event tasks.
 */
public class EventTask extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Constructs a EventTask with the given description and date.
     *
     * @param description Description of the event.
     * @param date Date of the event.
     */
    public EventTask(String description, LocalDate date) {
        super(description, 'E');
        this.date = date;
        this.startTime = null;
        this.endTime = null;
    }

    /**
     * Constructs a EventTask with the given description, date and starting time.
     *
     * @param description Description of the event.
     * @param date Date of the event.
     * @param startTime Starting time of the event.
     */
    public EventTask(String description, LocalDate date, LocalTime startTime) {
        super(description, 'E');
        this.date = date;
        this.startTime = startTime;
        this.endTime = null;
    }

    /**
     * Constructs a EventTask with the given description, date, starting and ending time.
     *
     * @param description Description of the event.
     * @param date Date of the event.
     * @param startTime Starting time of the event.
     * @param endTime Ending time of the event.
     */
    public EventTask(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description, 'E');
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private String getDateSaveString() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private String getStartTimeSaveString() {
        return this.startTime == null
                ? ""
                : " | " + this.startTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    private String getEndTimeSaveString() {
        return this.endTime == null
                ? ""
                : " | " + this.endTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Returns the format of string of event to be saved.
     *
     * @return String of event to be saved.
     */
    @Override
    public String getSaveString() {
        return super.getSaveString() + " | " + getDateSaveString()
                + getStartTimeSaveString() + getEndTimeSaveString();
    }

    /**
     * Returns the string of the event.
     *
     * @return string of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + getDateString()
                + getStartTimeString()
                + getEndTimeString()
                + ")";
    }

    private String getDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    private String getStartTimeString() {
        return this.startTime == null
                ? ""
                : " " + this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    private String getEndTimeString() {
        return this.endTime == null
                ? ""
                : "-" + this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }
}
