package task;

import simulator.DukeException;

import static simulator.Ui.printBox;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected LocalDate date = null;
    protected LocalTime time = null;

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public String getTime() {
        if (this.time != null) {
            return this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } else {
            return null;
        }
    }

    public Event(String description, String duration) throws DukeException {
        this.type = "E";
        if (description.length() != 0 && duration.length() != 0) {
            this.description = description;
            String[] input = duration.split("\\s+");
            try {
                this.date = LocalDate.parse(input[0]);
                this.time = LocalTime.parse(input[1]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                if (this.time == null) {
                    printBox("WARNING! No Time Detected!");
                } else if (this.date == null) {
                    throw new DukeException("☹ OOPS!!! Date of Event cannot be empty, please check!");
                } else {
                    throw new DukeException("☹ OOPS!!! Date & Time of Event cannot be empty, please check!");
                }
            }
        } else {
            if (description.length() == 0) {
                throw new DukeException("☹ OOPS!!! Description of Event cannot be empty, please check!");
            } else {
                throw new DukeException("☹ OOPS!!! Date of Event cannot be empty, please check!");
            }
        }
    }

    public Event(String status, String[] input) throws DukeException {
        this.type = "E";
        this.isDone = status.equals("complete");
        try {
            this.description = input[0];
            this.date = LocalDate.parse(input[1], DateTimeFormatter.ofPattern("dd MMM yyyy"));
            this.time = LocalTime.parse(input[2], DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (ArrayIndexOutOfBoundsException ex) {
            if (this.time == null) {
                printBox("WARNING!!! \n"
                        + "     Event with no Timing Specified Detected!");
            } else if (this.date == null) {
                throw new DukeException("☹ OOPS!!! Date of Event cannot be empty, please check!");
            } else {
                throw new DukeException("☹ OOPS!!! Description of Event cannot be empty, please check!");
            }
        }
    }

    @Override
    public String toString() {
        if (this.time == null) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(at: "
                    + this.getDate() + ")";
        } else {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(at: "
                    + this.getDate() + " "
                    + this.getTime() + ")";
        }
    }

}
