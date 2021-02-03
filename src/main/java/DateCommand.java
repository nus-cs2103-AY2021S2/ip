import java.time.LocalDate;
import java.util.List;

/**
 * Represents a command to print out all the tasks (<code>Deadline</code>, <code>Event</code>)
 * that falls on a particular date and time.
 */

public class DateCommand extends Command {
    /**
     * Constructor takes in a <code>fullCommand</code>, the full user input
     * consisting of the desired command, and a <code>action</code> which
     * in this case should specify "date"
     * @param fullCommand the full user input
     * @param action should be "date"
     */
    public DateCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    /**
     * Prints out all the tasks (<code>Deadline</code>/<code>Event</code>)
     * that falls on the user specified date in the <code>fullCommand</code>
     * @param tasks the task list consisting all the tasks
     * @throws DukeException if no date is specified
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response = "";
        String[] inputs = this.fullCommand.split(" ");
        if (inputs.length == 1) {
            throw new DukeException("OOPS! Please give me the date that you want to check in YYYY-MM-DD format.");
        }
        LocalDate d = LocalDate.parse(inputs[1]);
        List<Task> toPrint = tasks.print(d);
        for (int i = 1; i < toPrint.size() + 1; i++) {
            response = response + i + "." + toPrint.get(i - 1).toString() + "\n";
        }
        return response;
    }
}
