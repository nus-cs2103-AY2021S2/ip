import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    protected static final String type = "[E]";
    protected boolean isDone;
    protected LocalDateTime time;


    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
        this.isDone = false;
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (at: " + getTime() + ")";
    }

    /**
     * Parses an event description in duke.txt.
     *
     * @param record event description in duke.txt
     * @return an event object
     */
    public static Event parseEvent(String record) {
        if (record.contains("\u2713")) {
            String[] taskSeg = record.split("\u2713 ");
            Event e = getHistoryEvent(taskSeg);
            e.markAsDone();
            return e;
        } else {
            String[] taskSeg = record.split("\u2718 ");
            return getHistoryEvent(taskSeg);
        }
    }

    public static Event getHistoryEvent(String[] taskSeg) {
        String taskContent = taskSeg[taskSeg.length - 1];
        String taskContentWithDate = taskContent.replace(" (at:", "");
        taskContentWithDate = taskContentWithDate.replace(")", "");
        Pattern p = Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2}) ([0-9]{2}):([0-9]{2})");
        Matcher m = p.matcher(taskContentWithDate);
        m.find();
        String date = m.group();
        date = date.replace(" ", "T");
        LocalDateTime t = LocalDateTime.parse(date);
        String[] seg = taskContentWithDate.split(" ([0-9]{4})-([0-9]{2})-([0-9]{2}) ([0-9]{2}):([0-9]{2})");
        String myTask = seg[0];
        return new Event(myTask, t);
    }

}
