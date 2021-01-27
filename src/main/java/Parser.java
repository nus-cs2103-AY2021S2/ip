import java.io.IOException;
import java.util.Scanner;

/**
 * Parses the inputs given by the user.
 */
public class Parser {
    /**
     * Determines the keyword and processes the input based on the keyword.
     * @throws IOException In case of user errors.
     */
    static void parse() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean canExitNow = false;
        while (!canExitNow) {
            String inp = sc.nextLine();
            String[] spl = inp.split(" ", 2);
            try {
                switch (spl[0]) {
                case "todo":
                    checkSplLength(spl, 2, "todo");
                    TaskList.processTodo(spl);
                    break;
                case "deadline":
                    checkSplLength(spl, 2, "deadline");
                    String[] spl2 = spl[1].split(" /by ", 2);
                    checkSplLength(spl2, 2, "deadline");
                    TaskList.processDeadline(spl2);
                case "event":
                    checkSplLength(spl, 2, "event");
                    String[] spl3 =spl[1].split(" /at ", 2);
                    checkSplLength(spl3, 2, "event");
                    TaskList.processEvent(spl3);
                    break;
                case "done":
                    checkSplLength(spl, 2, "done");
                    isInt(spl[1]);
                    TaskList.processDone(spl);
                case "list":
                    checkSplLength(spl, 1, "list");
                    TaskList.processList();
                    break;
                case "delete":
                    checkSplLength(spl, 2, "delete");
                    isInt(spl[1]);
                    TaskList.processDelete(spl);
                    break;
                case "bye":
                    checkSplLength(spl, 1, "bye");
                    TaskList.processBye();
                    canExitNow = true;
                    break;
                case "find":
                    checkSplLength(spl, 2, "find");
                    TaskList.processFind(spl);
                    break;
                default:
                    throw new InvalidKeywordException();
                }
            } catch (InvalidTaskFormatException e) {
                e.printMessage();
            } catch (InvalidNumberException e) {
                e.printMessage();
            } catch (InvalidKeywordException e) {
                e.printMessage();
            }
        }
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
                throw new InvalidNumberException(Ui.InvalidNumberExceptionMessage());
            }
        } catch (NumberFormatException e){
            throw new InvalidNumberException(Ui.InvalidNumberExceptionMessage());
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
            throw new InvalidTaskFormatException(Ui.InvalidTaskFormatExceptionMessage(task));
        }
    }
}
