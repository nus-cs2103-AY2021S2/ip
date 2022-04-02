package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents tasks that can be classified as deadlines
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string of date formatted to the expected format
     * e.g. 2020-12-21 is returned as DEC 21 2020
     *
     * @param by the userinput for the deadline date
     * @return String of correctly formatted date
     */
    public String extractDateTime(String by) {
        String[] temp = by.split(" ");
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

    @Override
    public void updateTime(String time) {
        this.by = time;
    }

    /**
     * Returns a string of the format of task to be saved to text file
     * e.g. D | 1 | running | 2020-12-21 Sunday
     *
     * @return String of correctly formatted task
     */
    public String saveToFile() {
        return "D" + super.toString() + " | " + by;
    }

    /**
     * Returns a string of the format of task to be printed to console
     * e.g. D | 1 | running | DEC 21 2020 Sunday
     *
     * @return String of correctly formatted task
     */
    @Override
    public String toString() {
        return ("D" + super.toString() + " | " + extractDateTime(by));
    }
}
