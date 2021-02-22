package tasks;

import java.time.LocalTime;

public class Events extends DukeTask {
    private final String day;
    private final LocalTime start;
    private final LocalTime end;

    /**
     * Constructor for Event Task.
     * @param name Name of the Event.
     * @param day Day of the Event.
     * @param start Start Time of the Event.
     * @param end End Time of the Event.
     */
    public Events(String name, String day, String start, String end) {
        super(name, TaskType.EVENT);
        this.day = day;
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
    }

    /**
     * Constructor for Event Task.
     * @param name Name of the Event.
     * @param isDone Boolean of whether Event is done or not.
     * @param day Day of the Event.
     * @param start Start Time of the Event.
     * @param end End Time of the Event.
     */
    public Events(String name, boolean isDone, String day, String start, String end) {
        super(name, isDone, TaskType.EVENT);
        this.day = day;
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
    }

    private String convertDayTime() {
        return String.format("%s %s - %s", this.day, this.start.toString(), this.end.toString());
    }

    /**
     * Parse the date in String form into a String array of date and timings.
     *
     * @param datetime String of the entire DateTime.
     * @return String array of dates and timings.
     */
    public static String[] parseEvent(String datetime) {
        String[] days = datetime.split(" ", 2);
        assert(days.length == 2);

        String[] time = days[1].split(" - ", 2);
        assert(time.length == 2);

        return new String[]{days[0], time[0], time[1]};
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.convertDayTime());
    }

    @Override
    public String formatDuke() {
        return super.formatDuke() + " | " + this.convertDayTime();
    }

    @Override
    public DukeTask markDone() {
        return new Events(this.name, true, this.day, this.start.toString(),
                this.end.toString());
    }
}

