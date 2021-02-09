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
     * @param input user input.
     * @return an arraylist of Strings.
     * Elements of the arraylist are in the sequence:
     * command name, task index/description, date/error name (if applicable).
     */
    public ArrayList<String> parseInputToList(String input) {
//        String input = scan.nextLine();
        ArrayList<String> result = new ArrayList<>();

        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];
        result.add(command);

        switch (command) {
        case "bye":
        case "list":
            break;
        default:
            try {
                String details = inputArr[1];
//                String[] detailsArr = details.split(" ", 2);
//                String task = inputArr[0];
//                result.add(task);

//                String scannedInput = inputArr[1];
                String description, date, taskIndex;

                switch (command) {
                case "done":
                case "delete":
                    taskIndex = Integer.toString(Integer.parseInt(details) - 1);
                    result.add(taskIndex);
                    break;
                case "todo":
                case "find":
                    description = details;
                    result.add(description);
                    break;
                case "deadline":
                    String[] arrOfInputD = details.split(" /by ");
                    description = arrOfInputD[0];
                    date = arrOfInputD[1];

                    result.add(description);
                    result.add(date);
                    break;
                case "event":
                    String[] arrOfInputE = details.split(" /at ");
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
                if (command.equals("todo") || command.equals("deadline") || command.equals("event")
                        || command.equals("done") || command.equals("delete")) {
                    result.add("emptyDescError");
                } else {
//                    result.add("idkError");
                    result.add(command);
                }
            }
        }
        return result;
    }

}
