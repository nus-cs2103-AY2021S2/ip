import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Parses the inputs given by the user.
 */
public class Parser {
    private static String[] splitArray;
    /**
     * Determines the keyword and processes the input based on the keyword.
     */
    static String parse(String inp) {

        String[] splitArray = inp.split(" ", 2);
        assert splitArray.length > 0 : "Impossible input";

        String output;
        try {
            switch (splitArray[0]) {
            case "todo":
                output = processTodo();
                break;
            case "deadline":
                output = processDeadlineAndEvent(" /by ");
                break;
            case "event":
                output = processDeadlineAndEvent(" /at ");
                break;
            case "done":
                output = processDone();
                break;
            case "list":
                output = processList();
                break;
            case "delete":
                output = processDelete();
                break;
            case "bye":
                output = processBye();
                break;
            case "find":
                output = processFind();
                break;
            default:
                throw new InvalidKeywordException(Ui.invalidKeywordExceptionMessage());
            }
        } catch (InvalidTaskFormatException e) {
            output = e.printMessage();
        } catch (InvalidNumberException e) {
            output = e.printMessage();
        } catch (InvalidKeywordException e) {
            output = e.printMessage();
        }
        return output;
    }

    /**
     * Checks whether a given String can be parsed as an Integer.
     * @param s A string.
     * @throws InvalidNumberException Thrown in the case where the String is not an Integer.
     */
    static void isInt(String s) throws InvalidNumberException {
        try {
            int x = Integer.parseInt(s);
            if (x > TaskList.getCount()) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(Ui.invalidNumberExceptionMessage());
        }
    }

    /**
     * Checks whether an array (spl) is of the given length (x).
     * This determines whether the format of the input is correct.
     * @param spl Array of strings.
     * @param x Length the array is supposed to be checked for.
     * @param task To throw an error message dependent on the type of task.
     * @throws InvalidTaskFormatException Thrown is the array is not of the given length.
     */
    static void checkSplLength(String[] spl, int x, String task) throws InvalidTaskFormatException {
        if (spl.length != x) {
            throw new InvalidTaskFormatException(Ui.invalidTaskFormatExceptionMessage(task));
        }
    }

    static String processTodo() throws InvalidTaskFormatException {
        checkSplLength(splitArray, 2, "todo");
        String task = splitArray[0];
        String description = splitArray[1];
        return TaskList.processTaskOutput(task, description, null, null);
    }

    static String processDeadlineAndEvent(String regex) throws InvalidTaskFormatException {
        String task = splitArray[0];
        checkSplLength(splitArray, 2, task);

        String[] splitDescriptionAndTime = splitArray[1].split(regex, 2);
        checkSplLength(splitDescriptionAndTime, 2, task);

        String[] splitDateAndTime = splitDescriptionAndTime[1].split(" ", 2);
        checkSplLength(splitDateAndTime, 2, task);

        String description = splitDescriptionAndTime[0];
        LocalDate date = LocalDate.parse(splitDateAndTime[0]);
        LocalTime time = LocalTime.parse(splitDateAndTime[1]);

        return TaskList.processTaskOutput(task, description, date, time);
    }

    static String processDone() throws InvalidTaskFormatException, InvalidNumberException {
        checkSplLength(splitArray, 2, "done");
        isInt(splitArray[1]);
        int doneWithIndexNumber = Integer.parseInt(splitArray[1]);
        return TaskList.processDoneOutput(doneWithIndexNumber);
    }

    static String processList() throws InvalidTaskFormatException {
        checkSplLength(splitArray, 1, "list");
        return TaskList.processListOutput();
    }

    static String processDelete() throws InvalidTaskFormatException, InvalidNumberException {
        checkSplLength(splitArray, 2, "delete");
        isInt(splitArray[1]);
        int deleteThisIndexNumber = Integer.parseInt(splitArray[1]);
        return TaskList.processDeleteOutput(deleteThisIndexNumber);
    }

    static String processBye() throws InvalidTaskFormatException {
        checkSplLength(splitArray, 1, "bye");
        return TaskList.processBye();
    }

    static String processFind() throws InvalidTaskFormatException {
        checkSplLength(splitArray, 2, "find");
        return TaskList.processFindOutput(splitArray);
    }
}
