import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task{
    public LocalDateTime dlTime;

    public Deadline(String taskName, LocalDateTime dlTime) {
        super(taskName);
        this.dlTime = dlTime;
    }

    @Override
    public String printTask() {
        String ans;
        if (taskDone) {
            ans = "[D][X] " + this.taskName + " (by: "
                    + this.dlTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        } else {
            ans = "[D][ ] " + this.taskName + " (by: "
                    + this.dlTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        }
        return ans;
    }
}
