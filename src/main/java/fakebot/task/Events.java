package fakebot.task;

import java.time.LocalDate;
import java.time.LocalTime;

//Event Class with Date and Duration
public class Events extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    public Events(String name, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(name);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public String getTiming() {
        return saveDateFormat.format(startDate) + " " + saveDateFormat.format(startTime) + " " +
                saveDateFormat.format(endDate) + " " + saveDateFormat.format(endTime);

    }

    public String getStartDate() {
        return saveDateFormat.format(startDate);
    }

    public String getStartTime() {
        return saveTimeFormat.format(startTime);
    }

    public String getEndDate() {
        return saveDateFormat.format(endDate);
    }

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
