package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String location;
    protected String dateTime;

    public Event (String info, String afterAt) {
        super(info, taskType.Event);
        String[] afterAtList = afterAt.trim().split(" ", 2);
        this.location = afterAtList[0];
        this.dateTime = afterAtList[1];

    }

    public Event(String info, String afterAt, boolean isDone) {
        super(info, taskType.Event, isDone);
        String[] afterAtList = afterAt.split(" ", 2);
        this.location = afterAtList[0];
        this.dateTime = afterAtList[1];
    }

    public String getDateTime() {
        //Example of date based on format: 10 Aug 2021
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String[] dateList = this.dateTime.split(" ", 2);
        LocalDate eventDate = LocalDate.parse(dateList[0]);

        if (dateList.length == 2) {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
            LocalTime eventTime = LocalTime.parse(dateList[1]);

            return eventDate.format(dateFormat) + " " + eventTime.format(timeFormat);
        } else {
            return eventDate.toString();
        }
    }

    public String getLocation() {
        return this.location;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: "
                + getLocation() + " " + getDateTime() + ")";
    }
}
