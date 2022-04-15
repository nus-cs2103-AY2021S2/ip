import java.lang.reflect.InvocationTargetException;
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
                String description, date, taskIndex;

                switch (command) {
                case "done":
                case "delete":
                    taskIndex = Integer.toString(Integer.parseInt(details) - 1);
                    result.add(taskIndex);
                    break;
                case "edit":
                    String hasDescription = "0", hasDate = "0";
                    final String NO_CHANGE = "NO_CHANGE";
                    description = NO_CHANGE;
                    date = NO_CHANGE;

                    String[] arrOfEdit = details.split(" /", 3);
                    int taskIndexInt = Integer.parseInt(arrOfEdit[0]) - 1;
                    taskIndex = Integer.toString(taskIndexInt);
                    result.add(taskIndex);

                    for (int i = 1; i < arrOfEdit.length; i++) {
                        String partOfEdit = arrOfEdit[i];

                        String[] arrOfPartOfEdit = partOfEdit.split(" ", 2);
                        String attributeType = arrOfPartOfEdit[0];
                        String editedAttribute = arrOfPartOfEdit[1];

                        if (attributeType.equals("desc")) {
                            hasDescription = "1";
                            description = editedAttribute;
                        } else if (attributeType.equals("date")) {
                            hasDate = "1";
                            date = editedAttribute;
                        }
                    }
                    result.add(hasDescription);
                    result.add(description);
                    result.add(hasDate);
                    result.add(date);

                    break;
                case "find":
                case "todo":
                    description = details;
                    result.add(description);
                    break;
                case "deadline":
                    splitArgumentsByRegex(details, " /by ", result);
                    break;
                case "event":
                    splitArgumentsByRegex(details, " /at ", result);
                    break;
                default:
                    result.add("idkError");
                    break;
                }
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                final String EMPTY_DESC_ERROR = "emptyDescError";
                result.add(EMPTY_DESC_ERROR);
            }
        }
        assert(result.size() > 0);

        return result;
    }

    public void splitArgumentsByRegex(String details, String regex, ArrayList<String> result) {
        String[] arrOfInput = details.split(regex);
        String description = arrOfInput[0];
        String date = arrOfInput[1];

        result.add(description);
        result.add(date);
    }

}
