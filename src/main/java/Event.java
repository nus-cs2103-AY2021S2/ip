import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Task which is a event at a date and time.
 */
public class Event extends Task {
    public LocalDateTime event;

    public Event(String str) {
        super(str);//super must be 1st line..
        String[] split = str.split("/");
        if(split.length < 2) {
            throw new IllegalArgumentException();
        }
        this.task = split[0].trim();
        String trimmed = split[1].trim();
        String[] split1 = trimmed.split(" ");
        if(split1.length != 2) {
            throw new ArrayIndexOutOfBoundsException(Ui.lineGetter() + " Enter date and time " +
                    "in this format yyyy-mm-dd hh:mm\n" + Ui.lineGetter());
        }
        this.event = LocalDateTime.parse(split1[0].trim() + "T" + split1[1]);
    }

    public Event(String[] str, boolean isDone) {//call from harddisc
        super(str[2], isDone);
        this.event = LocalDateTime.parse(str[3]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" "+ "(at: "
                + event.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
