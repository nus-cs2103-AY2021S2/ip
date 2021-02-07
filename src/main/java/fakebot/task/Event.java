package fakebot.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event task type
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
     * Returns task start date.
     *
     * @return an string containing start date.
     */
    public String getStartDate() {
        return saveDateFormat.format(startDate);
    }

    /**
     * Returns task start time.
     *
     * @return an string containing start time.
     */
    public String getStartTime() {
        return saveTimeFormat.format(startTime);
    }

    /**
     * Returns task end date.
     *
     * @return an string containing end date.
     */
    public String getEndDate() {
        return saveDateFormat.format(endDate);
    }

    /**
     * Returns task end time.
     *
     * @return an string containing end time.
     */
    public String getEndTime() {
        return saveTimeFormat.format(endTime);
    }


    @Override
    public String toString() {
        String startDateTimeString = printDateFormat.format(startDate) + " " + printTimeFormat.format(startTime);
        String endDateTimeString = printDateFormat.format(endDate) + " " + printTimeFormat.format(endTime);
        return "[E]" + super.toString() + " (from: " + startDateTimeString + " to " + endDateTimeString + ")";
    }

    @Override
    public boolean equals(Object other) {
        boolean sameSuperClass = super.equals(other);
        if (!sameSuperClass || !(other instanceof Event)) {
            return false;
        }
        Event e2 = (Event) other;
        boolean sameStartDate = getStartDate().equals(e2.getStartDate());
        boolean sameStartTime = getStartTime().equals(e2.getStartTime());
        boolean sameEndDate = getEndDate().equals(e2.getEndDate());
        boolean sameEndTime = getEndTime().equals(e2.getEndTime());
        return sameStartDate && sameStartTime && sameEndDate && sameEndTime;
    }
}
