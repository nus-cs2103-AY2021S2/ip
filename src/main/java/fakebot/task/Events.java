package fakebot.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event Task Type
 */
public class Events extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * Class constructor specifying the task description, event start date, event start time,
     * event end date, event end time.
     */
    public Events(String name, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(name);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * Get Task Start Date.
     *
     * @return Return Start Date as String.
     */
    public String getStartDate() {
        return saveDateFormat.format(startDate);
    }

    /**
     * Get Task Start Time.
     *
     * @return Return Start Time as String.
     */
    public String getStartTime() {
        return saveTimeFormat.format(startTime);
    }

    /**
     * Get Task End Date.
     *
     * @return Return End Date as String.
     */
    public String getEndDate() {
        return saveDateFormat.format(endDate);
    }

    /**
     * Get Task End Time.
     *
     * @return Return End Time as String.
     */
    public String getEndTime() {
        return saveTimeFormat.format(endTime);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + printDateFormat.format(startDate) + " " + printTimeFormat.format(startTime) + " to " +
                printDateFormat.format(endDate) + " " + printTimeFormat.format(endTime) + ")";
    }
}
