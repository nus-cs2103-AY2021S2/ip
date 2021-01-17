/**
 * Provide methods for checking input format
 */
public class FormatChecker {

    /**
     * Check whether the user input string is in the format of getting task done
     * @param userInput user input string
     * @return True if the input string is trying to get task done;
     * the argument format, however, may be incorrect
     */
    public static boolean isTryingToGetTaskDone(String userInput) {
        try {
            String[] splited = userInput.split(" ");
            boolean isDone = splited[0].equals("done");
            return isDone;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check whether the user input string is in the format of listing tasks
     * @param userInput user input string
     * @return true if the input string is in the format of getting task done
     */
    public static boolean isPrintingList(String userInput) {
        return userInput.equals("list");
    }

    /**
     * Checks if the user input is trying to add a new task
     * @param userInput User input string
     * @return True if the using input string indicates adding a new task
     */
    public static boolean isAddingTask(String userInput) {
        return isToDoFormat(userInput)
                || isDeadLineFormat(userInput)
                || isEventFormat(userInput);
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
