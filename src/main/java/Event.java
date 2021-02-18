import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

class Event extends Task {
    private LocalDateTime date;

    Event(String task, LocalDateTime date) {
        super(task);
        this.date = date;
    }

    Event(String task, Boolean isCompleted, LocalDateTime date) {
        super(task, isCompleted);
        this.date = date;
    }

    /**
     * returns a Event if the line stored in Storage of a Task is of type Event
     *
     * @param input
     * @return Event created with reading from Storage file
     */
    public static Event readTaskFromStorage(String input) {
        String[] list = input.split(", ", 4);
        assert list.length == 4 : "Todo was stored from previous user visit is corrupted.";
        return new Event(list[2], Boolean.parseBoolean(list[1]), LocalDateTime.parse(list[3]));
    }

    /**
     * Gives a format of saving a Event into storage
     *
     * @return String formatted to save a Event into storage
     */
    public String saveInStorageAs() {
        return this.getClass().toString() + ", " + this.getCompleted() + ", " + this.getTask() + ", " + this.date;
    }
    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd YYYY hh:mm a");
        return "[E]" + super.toString()
                + "(at: " + dateFormatter.format(Timestamp.valueOf(this.date)) + ")";
    }
}
