import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    public Parser() {}

    /**
     * Parses user input Strings into an arraylist of Strings for easier access.
     *
     * @param scan the scanner used to scan user input.
     * @return an arraylist of Strings.
     * Elements of the arraylist are in the sequence:
     * command name, task index/description, date/error name (if applicable).
     */
    public ArrayList<String> parseInputToList(Scanner scan) {
        String input = scan.nextLine();
        ArrayList<String> result = new ArrayList<>();

        switch (input) {
            case "bye":
            case "list":
                result.add(input);
                break;
            default:
                try {
                    String[] inputArr = input.split(" ", 2);
                    String task = inputArr[0];
                    result.add(task);

                    String scannedInput = inputArr[1];
                    String description, date, taskIndex;

                    switch (task) {
                        case "done":
                        case "delete":
                            taskIndex = Integer.toString(Integer.parseInt(scannedInput) - 1);
                            result.add(taskIndex);
                            break;
                        case "todo":
                        case "find":
                            description = scannedInput;
                            result.add(description);
                            break;
                        case "deadline":
                            String[] arrOfInputD = scannedInput.split(" /by ");
                            description = arrOfInputD[0];
                            date = arrOfInputD[1];

                            result.add(description);
                            result.add(date);
                            break;
                        case "event":
                            String[] arrOfInputE = scannedInput.split(" /at ");
                            description = arrOfInputE[0];
                            date = arrOfInputE[1];

                            result.add(description);
                            result.add(date);
                            break;
                        default:
                            break;
                    }
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    if (input.equals("todo") || input.equals("deadline") || input.equals("event")
                            || input.equals("done") || input.equals("delete")) {
                        result.add("emptyDescError");
                    } else {
                        result.add("idkError");
                    }
                }
        }
        return result;
    }

}
