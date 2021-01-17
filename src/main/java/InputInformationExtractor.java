/**
 * Provide methods for extracting information from user input string
 */
public class InputInformationExtractor {
    /**
     * Get the content of the todo item from user input
     * @param userInput User input is assumed to be in todo format
     * @return Content of the todo item
     */
    public static String getToDoContent(String userInput) {
        return userInput.substring(5);
    }

    /**
     * Get the detail of the deadline item from user input
     * @param userInput User input is assumed to be in deadline format
     * @return Detail of the deadline item
     */
    public static String getDeadlineDetail(String userInput) {
        return userInput.substring(9);
    }

    /**
     * Get the content of the deadline item from user input
     * @param userInput User input is assumed to be in deadline format
     * @return Content of the deadline item
     */
    public static String getDeadlineContent(String userInput) {
        return userInput.split(" /by ")[0].substring(9);
    }

    /**
     * Get the time of the deadline item from user input
     * @param userInput User input is assumed to be in deadline format
     * @return Time of the deadline item
     */
    public static String getDeadlineTime(String userInput) {
        return userInput.split(" /by ")[1];
    }

    /**
     * Get the detail of the event item from user input
     * @param userInput User input is assumed to be in event format
     * @return detail of the event item
     */
    public static String getEventDetail(String userInput) {
        return userInput.substring(6);
    }

    /**
     * Get the content of the deadline item from user input
     * @param userInput User input is assumed to be in deadline format
     * @return Content of the deadline item
     */
    public static String getEventContent(String userInput) {
        return userInput.split(" /at ")[0].substring(6);
    }

    /**
     * Get the time of the deadline item from user input
     * @param userInput User input is assumed to be in deadline format
     * @return Time of the deadline item
     */
    public static String getEventTime(String userInput) {
        return userInput.split(" /at ")[1];
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
