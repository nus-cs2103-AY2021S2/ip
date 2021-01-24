import java.time.LocalDate;

/**
 * Provide methods for extracting information from user input string
 */
public class InputInformationExtractor {
    /**
     * Get the content of the todo item from user input
     * @param userInput User input is assumed to be in todo format
     * @return Content of the todo item
     * @throws ToDoException
     */
    public static String getToDoContent(String userInput) throws ToDoException {
        try {
            return userInput.substring(5);
        } catch (Exception e) {
            throw new ToDoException();
        }
    }

    /**
     * Get the detail of the deadline item from user input
     * @param userInput User input is assumed to be in deadline format
     * @return Detail of the deadline item
     * @throws DeadlineException
     */
    public static String getDeadlineDetail(String userInput) throws DeadlineException {
        try {
            return userInput.substring(9);
        } catch (Exception e) {
            throw new DeadlineException();
        }
    }

    /**
     * Get the content of the deadline item from user input
     * @param userInput User input is assumed to be in deadline format
     * @return Content of the deadline item
     * @throws DeadlineException
     */
    public static String getDeadlineContent(String userInput) throws DeadlineException {
        try {
            return userInput.split(" /by ")[0].substring(9);
        } catch (Exception e) {
            throw new DeadlineException();
        }
    }

    /**
     * Get the time of the deadline item from user input
     * @param userInput User input is assumed to be in deadline format
     * @return Local date of the deadline item
     * @throws DeadlineException
     */
    public static LocalDate getDeadlineTime(String userInput) throws DeadlineException {
        try {
            return LocalDate.parse(userInput.split(" /by ")[1]);
        } catch (Exception e) {
            throw new DeadlineException();
        }
    }

    /**
     * Get the detail of the event item from user input
     * @param userInput User input is assumed to be in event format
     * @return detail of the event item
     * @throws EventException
     */
    public static String getEventDetail(String userInput) throws EventException {
        try {
            return userInput.substring(6);
        } catch (Exception e) {
            throw new EventException();
        }
    }

    /**
     * Get the content of the deadline item from user input
     * @param userInput User input is assumed to be in deadline format
     * @return Content of the deadline item
     * @throws EventException
     */
    public static String getEventContent(String userInput) throws EventException {
        try {
            return userInput.split(" /at ")[0].substring(6);
        } catch (Exception e) {
            throw new EventException();
        }
    }

    /**
     * Get the time of the deadline item from user input
     * @param userInput User input is assumed to be in deadline format
     * @return Time of the deadline item
     * @throws EventException
     */
    public static String getEventTime(String userInput) throws EventException {
        try {
            return userInput.split(" /at ")[1];
        } catch (Exception e) {
            throw new EventException();
        }
    }

    /**
     * Get the index argument (at second place) from user input
     * @param userInput User input string
     * @return The index value in integer type, if applicable
     * @throws Exception If the input does not contain index value at second place,
     * throws exception
     */
    public static int getIndexArgument(String userInput) throws Exception {
        String[] splited = userInput.split(" ");
        int index = Integer.parseInt(splited[1]);
        return index;
    }
}
