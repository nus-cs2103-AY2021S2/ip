/**
 * Provide methods for checking input format
 */
public class FormatChecker {

    /**
     * Check whether the user input string is in the format of getting task done
     * @param userInput user input string
     * @return true if the input string is in the format of getting task done
     */
    public static boolean isDoneFormat(String userInput) {
        try {
            String[] splited = userInput.split(" ");
            boolean isDone = splited[0].equals("done");
            boolean hasInteger = isInteger((splited[1]));
            return isDone && hasInteger;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check whether the user input string is in the format of listing tasks
     * @param userInput user input string
     * @return true if the input string is in the format of getting task done
     */
    public static boolean isListFormat(String userInput) {
        return userInput.equals("list");
    }

    /**
     * Check whether the user input string is in the format of adding new ToDo task
     * @param userInput user input string
     * @return true if the input string is in the format of adding new ToDo task
     */
    public static boolean isToDoFormat(String userInput) {
        String[] splited = userInput.split(" ");
        boolean isToDo = splited[0].equals("todo");
        boolean notSingle = splited.length > 1;
        return isToDo && notSingle;
    }

    /**
     * Check whether the user input string is in the format of adding new deadline task
     * @param userInput user input string
     * @return true if the input string is in the format of adding new deadline task
     */
    public static boolean isDeadLineFormat(String userInput) {
        String[] splited = userInput.split(" ");
        boolean isDeadline = splited[0].equals("deadline");
        if (!isDeadline) {
            return false;
        }

        userInput = InputInformationExtractor.getDeadlineDetail(userInput);
        splited = userInput.split(" /by ");
        if (splited.length != 2) {
            return false;
        }

        return true;
    }

    /**
     * Check whether the user input string is in the format of adding new event task
     * @param userInput user input string
     * @return true if the input string is in the format of adding new event task
     */
    public static boolean isEventFormat(String userInput) {
        String[] splited = userInput.split(" ");
        boolean isEvent = splited[0].equals("event");
        if (!isEvent) {
            return false;
        }

        userInput = InputInformationExtractor.getEventDetail(userInput);
        splited = userInput.split(" /at ");
        if (splited.length != 2) {
            return false;
        }

        return true;
    }

    private static boolean isInteger(String toCheck) {
        try {
            Integer.parseInt(toCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
