import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.sql.Timestamp;

class Event extends Task {
    LocalDateTime date;

    Event(String task, LocalDateTime date) {
        super(task);
        this.date = date;
    }

    Event(String task, Boolean isCompleted, LocalDateTime date){
        super(task, isCompleted);
        this.date = date;
    }

    /**
     * blah test dw this
     * @param input
     * @return
     */
    public static Event readTask(String input) {
        String[] list = input.split(", ", 4);
        return new Event(list[2], Boolean.parseBoolean(list[1]), LocalDateTime.parse(list[3]));
    }

    public String toCommand() {
        return this.getClass().toString() + ", " + this.getCompleted() + ", " + this.task + ", " + this.date;
    }
    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd YYYY hh:mm a");
        return "[E]" + this.completedBox() + this.task 
                    + "(at: " + dateFormatter.format(Timestamp.valueOf(this.date)) + ")";
    }
}