package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reminder {
    private final LocalDateTime localDateTime;

    public Reminder(LocalDateTime date) {
        this.localDateTime = date;
    }

    public static Task createReminder(String input, ArrayList<Task> tasks) {
        int indexOfOn = input.lastIndexOf("/on ");
        String date = input.substring(indexOfOn + 3).trim();
        int taskIndex = Integer.parseInt(input.substring(0, indexOfOn).strip()) - 1;
        tasks.get(taskIndex).addReminder(new ParseDates().parseString(date));
        return tasks.get(taskIndex);
    }

    public LocalDateTime getDate() {
        return this.localDateTime;
    }

    public String getReminderDateOnly() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return localDateTime.format(dateTimeFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "Reminder! At: " + localDateTime.format(dateTimeFormatter) + " for:\n";
    }
}
