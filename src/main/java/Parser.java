import java.util.Scanner;

/**
 * Parses the inputs given by the user.
 */
public class Parser {
    /**
     * Determines the keyword and processes the input based on the keyword.
     */
    static String parse(String inp) {
        String[] spl = inp.split(" ", 2);
        String output;
        try {
            switch (spl[0]) {
            case "todo":
                checkSplLength(spl, 2, "todo");
                output = TaskList.processTodo(spl);
                break;
            case "deadline":
                checkSplLength(spl, 2, "deadline");
                String[] spl2 = spl[1].split(" /by ", 2);
                checkSplLength(spl2, 2, "deadline");
                String[] spl3 = spl2[1].split(" ", 2);
                checkSplLength(spl3, 2, "deadline");
                output = TaskList.processDeadline(spl2, spl3);
                break;
            case "event":
                checkSplLength(spl, 2, "event");
                String[] spl4 = spl[1].split(" /at ", 2);
                checkSplLength(spl4, 2, "event");
                String[] spl5 = spl4[1].split(" ", 2);
                checkSplLength(spl5, 2, "event");
                output = TaskList.processEvent(spl4, spl5);
                break;
            case "done":
                checkSplLength(spl, 2, "done");
                isInt(spl[1]);
                output = TaskList.processDone(spl);
                break;
            case "list":
                checkSplLength(spl, 1, "list");
                output = TaskList.processList();
                break;
            case "delete":
                checkSplLength(spl, 2, "delete");
                isInt(spl[1]);
                output = TaskList.processDelete(spl);
                break;
            case "bye":
                checkSplLength(spl, 1, "bye");
                output = TaskList.processBye();
                break;
            case "find":
                checkSplLength(spl, 2, "find");
                output = TaskList.processFind(spl);
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
                throw new InvalidNumberException(Ui.invalidNumberExceptionMessage());
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
}
