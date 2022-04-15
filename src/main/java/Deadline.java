import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    protected boolean isDone;
    protected LocalDate time;
    protected final static String type = "[D]";

    public Deadline(String description, LocalDate time) {
        super(description);
        this.isDone = false;
        this.time = time;
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (by: " + getTime() + ")";
    }

    /**
     * Parses a deadline description in duke.txt.
     *
     * @param record deadline description in duke.txt
     * @return a deadline object
     */
    public static Deadline parseDeadline(String record) {
        if (record.contains("\u2713")) {
            String[] taskSeg = record.split("\u2713 ");
            Deadline d = getHistoryDeadline(taskSeg);
            d.markAsDone();
            return d;
        } else {
            String[] taskSeg = record.split("\u2718 ");
            return getHistoryDeadline(taskSeg);

        }
    }

    public static Deadline getHistoryDeadline(String[] taskSeg) {
        String taskContent = taskSeg[taskSeg.length - 1];
        String taskContentWithDate = taskContent.replace(" (by:", "");
        taskContentWithDate = taskContentWithDate.replace(")", "");
        Pattern p = Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2})");
        Matcher m = p.matcher(taskContentWithDate);
        m.find();
        String date = m.group();
        LocalDate t = LocalDate.parse(date);
        String[] seg = taskContentWithDate.split(" ([0-9]{4})-([0-9]{2})-([0-9]{2})");
        String myTask = seg[0];
        return new Deadline(myTask, t);
    }

}
