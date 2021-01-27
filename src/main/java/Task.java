import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String task;
    private boolean done = false;
    private final int type; // 0 is todo, 1 is deadline, 2 is event
    private final LocalDate date;
    private final LocalTime time;

    public Task(String task, int type) {
        if (type == 0) {
            this.task = task;
            this.date = null;
            this.time = null;
        } else {
            String[] info = task.split("/");
            if (info.length == 1 || info[0].equals(" ")) {
                throw new IllegalArgumentException();
            } else if (type == 1) {
                this.date = LocalDate.parse(info[1].substring(3));
                this.time = null;
            } else {
                this.date = null;
                this.time = LocalTime.parse(info[1].substring(3));
            }
            this.task = info[0];
        }
        this.type = type;
    }

    public void markDone() {
        this.done = true;
    }

    public String checkDone() {
        if (this.done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String checkType() {
        if (this.type == 0) {
            return "[T]";
        } else if (this.type == 1) {
            return "[D]";
        } else {
            return "[E]";
        }
    }
    
    public String toString() {
        if (this.type == 0) {
            return checkType() + checkDone() + this.task;
        } else if  (this.type == 1) {
            return checkType() + checkDone() + this.task + "(by: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return checkType() + checkDone() + this.task + "(at: "
                    + this.time.format(DateTimeFormatter.ofPattern("hh:mm a"))+ ")";
        }
    }

    public String export() {
        String done = this.done ? " 1" : " 0";
        String deadline = this.type == 0 ? "" : this.type == 1 ? "/by " + this.date : "/at "
                + this.time;
        return this.type + done + this.task + deadline;
    }
}
