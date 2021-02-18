/**
 * A type of Task with a date and time.
 */
public class Event extends Task {
    public Event(String description, String date, String time) {
        super(description, date, time, "[E]", false);
    }

    /**
     * Converts the start and end time from 24h to 12h format.
     */
    public void formatStartEndTime() {
        String timeCopy = getTime();
        String[] timeArr = timeCopy.split("-");
        String startTime = timeArr[0];
        String endTime = timeArr[1];
        String formattedStartTime = formatTime(startTime);
        String formattedEndTime = formatTime(endTime);
        String formattedTime = formattedStartTime + "-" + formattedEndTime;
        setTime(formattedTime);
    }
}