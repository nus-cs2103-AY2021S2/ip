import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.sql.Timestamp;

class Deadline extends Task {
    LocalDateTime date;

    Deadline(String t, LocalDateTime due) {
        super(t);
        this.date = due;
    }

    Deadline(String task, Boolean isCompleted, LocalDateTime dueDate) {
        super(task, isCompleted); 
        this.date = dueDate;
    }

    public static Deadline readTask(String input) {
        String[] list = input.split(", ", 4);
        return new Deadline(list[2], Boolean.parseBoolean(list[1]), LocalDateTime.parse(list[3]));
    }

    public String toCommand() {
        return this.getClass().toString() + ", " + 
                this.getCompleted() + ", " + this.task + ", " + this.date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd YYYY hh:mm a");
        // return "[D]" + this.completedBox() + this.task 
        //         + "(by: " + this.date.getMonth() + " " + this.date.getDayOfMonth() + " " +  this.date.getYear() +  " " + this.date.toLocalTime() +  ")";
        return "[D]" + this.completedBox() + this.task 
                + "(by: " + dateFormatter.format(Timestamp.valueOf(this.date)) + ")";
    }

}