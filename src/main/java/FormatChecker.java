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
     * Check whether the user is trying to add a new task
     * @param userInput User input string
     * @return True if the user is trying to add a new task;
     * the argument, however, may not be correct
     */
    public static boolean isTryingToAddTask(String userInput) {
        return likeAddingToDo(userInput) || likeAddingDeadline(userInput) || likeAddingEvent(userInput);
    }

    /**
     * Check whether the user input string seems to be the format of adding new ToDo task
     * @param userInput user input string
     * @return true if the input string seems to be the format of adding new ToDo task
     */
    public static boolean likeAddingToDo(String userInput) {
        String[] splited = userInput.split(" ");
        boolean isToDo = splited[0].equals("todo");
        return isToDo;
    }

    /**
     * Check whether the user input string seems to be the format of adding new Deadline task
     * @param userInput user input string
     * @return true if the input string seems to be the format of adding new Deadline task
     */
    public static boolean likeAddingDeadline(String userInput) {
        String[] splited = userInput.split(" ");
        boolean isDeadline = splited[0].equals("deadline");
        return isDeadline;
    }

    /**
     * Check whether the user input string seems to be the format of adding new Event task
     * @param userInput user input string
     * @return true if the input string seems to be the format of adding new Event task
     */
    public static boolean likeAddingEvent(String userInput) {
        String[] splited = userInput.split(" ");
        boolean isEvent = splited[0].equals("event");
        return isEvent;
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
