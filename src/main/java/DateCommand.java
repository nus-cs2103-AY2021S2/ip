import java.time.LocalDate;
import java.util.List;

/**
 * Represents a command to print out all the tasks that falls on a particular date and time.
 */

public class DateCommand extends Command {
    /**
     * Constructor that takes in two parameters, <code>fullCommand</code> and <code>typeOfCommand</code>.
     * @param fullCommand the full user input
     * @param typeOfCommand the type of command to be executed, in this case should be "date"
     */
    public DateCommand(String fullCommand, String typeOfCommand) {
        super(fullCommand, typeOfCommand);
    }

    /**
     * Prints out all the tasks that falls on the user specified date
     * @param tasks the task list consisting all the tasks
     * @return a String consisting a list of the tasks on the specified date
     * @throws DukeException if no date is specified
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response = "";
        String[] splitInputs = this.fullCommand.split(" ");
        if (splitInputs.length == 1) {
            throw new DukeException("OOPS! Please give me the date that you want to check in YYYY-MM-DD format.");
        }
        LocalDate parsedDate = LocalDate.parse(splitInputs[1]);
        List<Task> toPrint = tasks.listTasksOnDate(parsedDate);
        for (int i = 1; i < toPrint.size() + 1; i++) {
            response = response + i + "." + toPrint.get(i - 1).toString() + "\n";
        }
        return response;
    }
}
