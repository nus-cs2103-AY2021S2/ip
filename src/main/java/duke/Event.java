package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a task event when user wants to deal with an Event
 */
public class Event extends Task {

    protected String at;

    /**
     * Class constructor
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string of date formatted to the expected format
     * e.g. 2020-12-21 is returned as DEC 21 2020
     *
     * @param at the userinput for the event date
     * @return String of correctly formatted date
     */
    public String extractDateTime(String at) {
        String[] temp = at.split(" ");
        String result = "";
        for (String str : temp) {
            System.out.println(str);
        }

        LocalDate date = LocalDate.parse(temp[0]);
        result = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        if (temp.length > 1) {
            LocalTime time = LocalTime.parse(temp[1]);
            String res = time.format(DateTimeFormatter.ofPattern("HH:mm"));
            result += (" " + res);
        }

        return result;
    }

    /**
     * Returns a string of the format of task to be saved to text file
     * e.g. E | 1 | running | 2020-12-21 Sunday
     *
     * @return String of correctly formatted task
     */
    public String saveToFile() {
        return "E" + super.toString() + " | " + at;
    }

    @Override
    public void updateTime(String time) {
        this.at = time;
    }

    /**
     * Returns a string of the format of task to be printed to console
     * e.g. E | 1 | running | DEC 21 2020 Sunday
     *
     * @return String of correctly formatted task
     */
    @Override
    public String toString() {
        return "E" + super.toString() + " | " + extractDateTime(at);
    }
}
