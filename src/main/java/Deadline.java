import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String dueDate;

    public final static Deadline createDeadline(String input) throws DukeException {
        String name = input.substring(8);
        if (name.equals("") || name.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        name = name.substring(1);
        int cutoff = name.indexOf("/by");
        if (cutoff == -1) {
            throw new DukeException("☹ OOPS!!! Deadlines need a due date! Use \"/by\" to indicate due dates!");
        }

        String deadlineName = name.substring(0, cutoff - 1);
        String deadlineDueDate = name.substring(cutoff + 3);
        if (deadlineDueDate.equals("") || deadlineDueDate.equals(" ")) {
            throw new DukeException("☹ OOPS!!! Deadlines need a due date!");
        }

        deadlineDueDate = deadlineDueDate.substring(1);
        LocalDate actualDueDate;
        try {
            actualDueDate = LocalDate.parse(deadlineDueDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter your date in YYYY-MM-DD format!!!");
        }

        System.out.println(actualDueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return new Deadline(deadlineName, deadlineDueDate);
    }

    protected Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    protected Deadline(String name, String dueDate, boolean completed) {
        super(name, completed);
        this.dueDate = dueDate;
    }

    @Override
    public Task completeTask() {
        return new Deadline(this.name, this.dueDate, true);
    }

    @Override
    public String toString() {
        return super.toString().replace("[T]", "[D]") + " (by: " + this.dueDate + ")";
    }

}