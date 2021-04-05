package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDateTime localDate;

    public Event(String input, String date) {
        super(input);
        this.localDate = new ParseDates().parseString(date);
    }

    public Event(String input, String date, int done, String reminderDate) {
        super(input);
        this.localDate = new ParseDates().parseString(date);
        if (done == 1) {
            this.doTask();
        }
        if (!reminderDate.equals("0")) {
            this.addReminder(new ParseDates().parseString(reminderDate));
        }
    }

    public static Event createEvent(String input) {
        int indexOfAt = input.lastIndexOf("/at ");
        String date = input.substring(indexOfAt + 3).trim();
        return new Event(input.substring(0, indexOfAt).strip(), date);
    }

    @Override
    public String saveTask() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "E" + super.saveTask() + " | " + localDate.format(dateTimeFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (at: " + localDate.format(dateTimeFormatter) + ")";
    }
}
