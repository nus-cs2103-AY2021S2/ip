import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Events extends DukeTask {
    private final DayOfWeek day;
    private final LocalTime start;
    private final LocalTime end;

    public Events(String name, String day, LocalTime start, LocalTime end) {
        super(name, TaskType.EVENT);
        this.day = DayOfWeek.valueOf(day);
        this.start = start;
        this.end = end;
    }

    public Events(String name, boolean isDone, String day, LocalTime start, LocalTime end) {
        super(name, isDone, TaskType.EVENT);
        this.day = DayOfWeek.valueOf(day);
        this.start = start;
        this.end = end;
    }

    private String convertDayTime() {
        return String.format("%s %s - %s", this.day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                this.start.toString(), this.end.toString());
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.convertDayTime() );
    }

    @Override
    public String formatDuke() {
        return super.formatDuke() + " | " + this.time;
    }

    @Override
    public DukeTask markDone() {
        return new Events(this.name, true, this.time);
    }
}

