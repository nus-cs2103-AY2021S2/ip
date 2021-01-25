import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    public LocalDateTime eTime;

    public Event(String taskName, LocalDateTime eTime) {
        super(taskName);
        this.eTime = eTime;
    }

    @Override
    public String printTask() {
        String ans;
        if (taskDone) {
            ans = "[E][X] " + this.taskName + " (at: "
                    + this.eTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        } else {
            ans = "[E][ ] " + this.taskName + " (at: "
                    + this.eTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        }
        return ans;
    }
}

