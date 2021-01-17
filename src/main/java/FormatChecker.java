/**
 * Provide methods for checking input format
 */
public class FormatChecker {

    /**
     * Check whether the user input string is in the format of getting item done
     * @param userInput user input string
     * @return true if the input string is in the format of getting item done
     */
    public static boolean isDoneFormat(String userInput) {
        String[] splited = userInput.split(" ");
        return splited[0].equals("done") && isInteger(splited[1]);
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
