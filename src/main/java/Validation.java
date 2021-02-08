import java.util.Arrays;
import java.util.List;

/**
 * Validation handles the initial validation of the user command inputs.
 */
public class Validation {

    public static final int VALID_INDEX_BOUND = -1;
    public static final int INDEX_OFFSET = 1;

    /**
     * Checks if the user input is within the list of accepted commands.
     *
     * @param command User's command input.
     * @throws DukeException On invalid input.
     */
    public static void checkValidCommand(String command) throws DukeException {
        String[] validCommands = new String[]{"todo", "deadline", "event", "list", "bye", "done", "delete", "find"};
        List<String> commands = Arrays.asList(validCommands);

        int index = command.indexOf(' ');
        String first = "";
        if (index > VALID_INDEX_BOUND) {
            first = command.substring(0, index);
        } else {
            first = command;
        }

        if (!commands.contains(first.toLowerCase())) {
            throw new DukeException(":( OOPS! I'm sorry, but I don't know what that means!!!");
        } else {
            String[] secondValidation = new String[] {"todo", "deadline", "event", "done", "delete", "find"};
            List<String> secondListOfCommands = Arrays.asList(secondValidation);
            boolean isCommandInSecondList = secondListOfCommands.contains(first.toLowerCase());

            if (isCommandInSecondList
                    && (index <= VALID_INDEX_BOUND || command.substring(index).isBlank())) {
                throw new DukeException(":( OOPS! The description of a todo/deadline/event/done/delete/find "
                        + "cannot be empty!!");
            }
        }
    }

    /**
     * Checks if the time/date input with Events/Deadlines are valid.
     *
     * @param command User's command input.
     * @param findSlash Position of slash in the input.
     * @throws DukeException On invalid time/date input.
     */
    public static void checkForSchedule(String command, int findSlash) throws DukeException {
        boolean isSlashAbsent = (findSlash == VALID_INDEX_BOUND);
        boolean isDescriptionNotComplete = command.endsWith("/");
        boolean isDescriptionNotValid = command.substring(findSlash + INDEX_OFFSET).isBlank();

        if (isSlashAbsent || isDescriptionNotComplete || isDescriptionNotValid) {
            throw new DukeException(":( OOPS! Please input a valid time/date");
        }
    }

    /**
     * Checks if task that user has identified exists.
     *
     * @param taskSize Number of tasks in the TaskList.
     * @param chosenNumber The task number.
     * @throws DukeException On invalid input. Task does not exist.
     */
    public static void checkValidRange(int taskSize, int chosenNumber) throws DukeException {
        boolean isOutOfBounds = chosenNumber > taskSize;
        if (isOutOfBounds) {
            throw new DukeException(":( OOPS! This task does not exist! Use 'list' to check your task numbers!");
        }
    }
}
