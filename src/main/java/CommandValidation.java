import java.util.Arrays;
import java.util.List;

/**
 * CommandValidation handles the initial validation of the user command inputs.
 */
public class CommandValidation {

    public static final int VALID_INDEX_BOUND = -1;
    public static final int INDEX_OFFSET = 1;
    public static final int STARTING_INDEX = 0;

    /**
     * Checks if the user input is within the list of accepted commands.
     *
     * @param command User's command input.
     * @throws DukeException On invalid input.
     */
    public static void checkValidCommand(String command) throws DukeException {
        String[] validCommands = new String[] {"todo", "deadline", "event", "list", "bye", "done", "delete",
                "find", "tag", "help"};
        List<String> commands = Arrays.asList(validCommands);

        int index = command.indexOf(' ');
        String first = command;
        if (index > VALID_INDEX_BOUND) {
            first = command.substring(0, index);
        }
        if (!commands.contains(first)) {
            throw new DukeException(":( OOPS! I'm sorry, but I don't know what that means!!! Tip: commands should "
                     + "not be in capital letters!");
        } else {
            String[] secondValidation = new String[] {"todo", "deadline", "event", "done", "delete", "find", "tag"};
            List<String> secondListOfCommands = Arrays.asList(secondValidation);
            boolean isCommandInSecondList = secondListOfCommands.contains(first);

            if (isCommandInSecondList
                    && (index <= VALID_INDEX_BOUND || command.substring(index).isBlank())) {
                throw new DukeException(":( OOPS! The description of a todo/deadline/event/done/delete/find/tag "
                        + "cannot be empty!!");
            }
        }
    }

    /**
     * Checks if the tag/deadline/event commands are valid.
     *
     * @param command User's command input.
     * @param findSlash Position of slash in the input.
     * @throws DukeException On invalid input.
     */
    public static void checkForContentAfterSlash(String command, int findSlash) throws DukeException {
        boolean isSlashAbsent = (findSlash == VALID_INDEX_BOUND);
        boolean isDescriptionNotComplete = command.endsWith("/");
        boolean isDescriptionNotValid = command.substring(findSlash + INDEX_OFFSET).isBlank();

        int index = command.indexOf(' ');
        String commandType = command.substring(STARTING_INDEX, index);

        if (isSlashAbsent || isDescriptionNotComplete || isDescriptionNotValid) {
            if (commandType.equals("tag")) {
                throw new DukeException(":( OOPS! Please input a valid tag");
            } else {
                throw new DukeException(":( OOPS! Please input a valid time/date");
            }
        }
    }

    /**
     * Checks if the syntax for commands with time and date is correct.
     *
     * @param taskType A Character indicating the type of task.
     * @param command  User's command input.
     * @param findSlash Position of slash in the input.
     * @throws DukeException On invalid input.
     */
    public static void validateSyntaxForSettingTimeDate(
            Character taskType, String command, int findSlash) throws DukeException {

        String subcommand = command.substring(findSlash + INDEX_OFFSET);
        int findSpace = subcommand.indexOf(' ');
        String descriptionAfterSlash = subcommand.substring(STARTING_INDEX, findSpace);

        switch (taskType) {
        case 'E':
            if (!descriptionAfterSlash.equals("at")) {
                throw new DukeException(":( OOPS the command syntax is wrong, check out the help page for the "
                        + "correct syntax!");
            }
            break;
        case 'D':
            if (!descriptionAfterSlash.equals("by")) {
                throw new DukeException(":( OOPS the command syntax is wrong, check out the help page for the "
                        + "correct syntax!");
            }
            break;
        default:
            break;
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
        boolean isOutOfBounds = chosenNumber > taskSize | chosenNumber <= 0;
        if (isOutOfBounds) {
            throw new DukeException(":( OOPS! This task does not exist! Use 'list' to check your task numbers!");
        }
    }
}
