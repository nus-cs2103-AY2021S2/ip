import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles commands that adds Tasks to the list
 */
public class AddCommand extends Command {

    /**
     * Creates and initializes an AddCommand object.
     *
     * @param input The command that the user wishes to use.
     * @param parts The entire array of strings passed in by user.
     * @param tasks The list of tasks currently used.
     */
    AddCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }

    public AddCommand() {
        super();
    }

    /**
     * Returns a string representation of the Task after it has been added to the list.
     *
     * @return A string representation of the Task added to the list.
     * @throws DukeException If no arguments are provided.
     */
    @Override
    public String execute() throws DukeException {
        try {
            if (parts.length == 1) {
                throw new DukeException("Insufficient arguments provided");
            }
            if (input.equals("todo")) {
                return new TodoCommand(input, parts, tasks).getString();
            } else {
                int slashIndex = getSlashIndex();
                String taskString = getTaskString(slashIndex);
                String dateString = getDateString(slashIndex);
                LocalDate date = LocalDate.parse(dateString);
                if (input.equals("deadline")) {
                    return new DeadlineCommand(taskString, date, tasks).getString();
                } else {
                    return new EventCommand(taskString, date, tasks).getString();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Input is not in the right format");
        } catch (DateTimeParseException e) {
            throw new DukeException("Date is not in the right format");
        }
    }

    /**
     * Returns the description of the task entered by the user.
     *
     * @param slashIndex The position where the slash is located.
     * @return A string that describes the task entered by the user.
     */
    public String getTaskString(int slashIndex) {
        StringBuilder str = new StringBuilder();
        for (int j = 1; j < slashIndex; j++) {
            str.append(" ");
            str.append(parts[j]);
        }
        return str.toString();
    }

    /**
     * Calculates the position of the slash in the command entered.
     *
     * @return Integer which represents the index of the slash.
     */
    public int getSlashIndex() {
        int slashIndex = 0;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains(Character.toString('/'))) {
                slashIndex = i;
            }
        }
        return slashIndex;
    }

    /**
     * Returns the date entered by the user.
     *
     * @param slashIndex The position where the slash is located.
     * @return A string that describes the date entered by the user.
     */
    public String getDateString(int slashIndex) {
        StringBuilder byStringBuilder = new StringBuilder();
        for (int k = slashIndex + 1; k < parts.length; k++) {
            if (k != slashIndex + 1) {
                byStringBuilder.append(" ");
            }
            byStringBuilder.append(parts[k]);
        }
        return byStringBuilder.toString();
    }
}
