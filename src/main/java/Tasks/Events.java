package Tasks;

import java.time.LocalTime;

public class Events extends DukeTask {
    private final String day;
    private final LocalTime start;
    private final LocalTime end;

    public Events(String name, String day, String start, String end) {
        super(name, TaskType.EVENT);
        this.day = day;
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
    }

    public Events(String name, boolean isDone, String day, String start, String end) {
        super(name, isDone, TaskType.EVENT);
        this.day = day;
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
    }

    private String convertDayTime() {
        return String.format("%s %s - %s", this.day, this.start.toString(), this.end.toString());
    }

    public static String[] parseEvent(String date) {
        String[] days = date.split(" ", 2);
        String[] time = days[1].split(" - ", 2);

        return new String[]{days[0], time[0], time[1]};
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.convertDayTime() );
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

