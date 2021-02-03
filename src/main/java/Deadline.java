import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

class Deadline extends Task {
    private LocalDateTime date;

    Deadline(String t, LocalDateTime due) {
        super(t);
        this.date = due;
    }

    Deadline(String task, Boolean isCompleted, LocalDateTime dueDate) {
        super(task, isCompleted);
        this.date = dueDate;
    }

    /**
     * returns a Deadline if the line stored in Storage of a Task is of type Deadline
     *
     * @param input
     * @return Deadline created with reading from Storage file
     */
    public static Deadline readTask(String input) {
        String[] list = input.split(", ", 4);
        return new Deadline(list[2], Boolean.parseBoolean(list[1]), LocalDateTime.parse(list[3]));
    }

    /**
     * Gives a format of saving a Deadline into storage
     *
     * @return String formatted to save a Deadline into storage
     */
    public String toCommand() {
        return this.getClass().toString() + ", "
                + this.getCompleted() + ", " + this.getTask() + ", " + this.date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd YYYY hh:mm a");
        return "[D]" + this.completedBox() + this.getTask()
                + "(by: " + dateFormatter.format(Timestamp.valueOf(this.date)) + ")";
    }

}
