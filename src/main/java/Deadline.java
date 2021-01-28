import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String des, LocalDateTime by) {
        super(des);
        this.by = by;
    }

    /**
     * makes a deadline with the string given
     * @param line input string that will be split into the task description and date
     * @return a Deadline
     * @throws DukeException
     */

    public static Deadline makeDeadline(String line) throws DukeException {
        if (line.equals("")) {
            throw new DukeException("☹ OOPS!!!The description of a todo cannot be empty.\n");
        } else {
            String[] split = line.split("/by ");
            String task = split[0];
            String[] temp = split[1].split(" ");
            String date = temp[0];
            LocalTime time = LocalTime.parse(temp[1]);
            LocalDate d = LocalDate.parse(date);
            LocalDateTime dt = d.atTime(time);
            //System.out.println(d.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            return new Deadline(task, dt);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h a")) + ")";
    }
}