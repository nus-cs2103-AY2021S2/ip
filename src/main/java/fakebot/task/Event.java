package fakebot.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event Task Type
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * Class constructor specifying the task description, event start date, event start time,
     * event end date, event end time.
     */
    public Event(String name, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(name);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * Returns Task Start Date.
     *
     * @return Return Start Date as String.
     */
    public String getStartDate() {
        return saveDateFormat.format(startDate);
    }

    /**
     * Returns Task Start Time.
     *
     * @return Return Start Time as String.
     */
    public String getStartTime() {
        return saveTimeFormat.format(startTime);
    }

    /**
     * Returns Task End Date.
     *
     * @return Return End Date as String.
     */
    public String getEndDate() {
        return saveDateFormat.format(endDate);
    }

    /**
     * Returns Task End Time.
     *
     * @return Return End Time as String.
     */
    public String getEndTime() {
        return saveTimeFormat.format(endTime);
    }


    @Override
    public String toString() {
        String startDateTimeString = printDateFormat.format(startDate) + " " + printTimeFormat.format(startTime);
        String endDateTimeString = printDateFormat.format(endDate) + " " + printTimeFormat.format(endTime);
        return "[E]" + super.toString() + " (from: " + startDateTimeString + " to " + endDateTimeString+ ")";
    }
}
