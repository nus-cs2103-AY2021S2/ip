import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a deadline date and time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String input) {
        super(input);
        String[] split = input.split("/");
        if (split.length < 2) {
            throw new IllegalArgumentException();
        }
        this.task = split[0].trim();
        String trimmed = split[1].trim();
        String[] split1 = trimmed.split(" ");
        if (split1.length != 2) {
            throw new ArrayIndexOutOfBoundsException(Ui.deadlineFormat());
        }
        this.deadline = LocalDateTime.parse(split1[0].trim() + "T" + split1[1]);
    }

    //use this constructor if calling from harddisc
    public Deadline(String[] input, boolean isDone) {
        super(input[2], isDone);
        this.deadline = LocalDateTime.parse(input[3]);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
