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
        assert tasks != null : "tasks cannot be null";
        try {
            if (parts.length == 1) {
                throw new DukeException("Insufficient arguments provided");
            }
            if (input.equals("todo")) {
                return new TodoCommand(input, parts, tasks).getString();
            } else {
                int slashIndex = getSlashIndex();
                String taskString = getTaskString(slashIndex);
                String dateString = parts[slashIndex + 1];
                String prepositionString = parts[slashIndex];
                LocalDate date = LocalDate.parse(dateString);
                if (input.equals("deadline") && prepositionString.equals("/by")) {
                    return new DeadlineCommand(taskString, date, tasks).getString();
                }
                if (input.equals("event") && prepositionString.equals("/at")) {
                    return new EventCommand(taskString, date, tasks).getString();
                } else {
                    throw new DukeException("Wrong preposition used!\n Use 'by' for Deadlines " +
                            "and 'at' for Events");
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
}
