package switchblade;

/**
 * switchblade.Parser will be used to manipulate any String to retrieve the desired output
 *
 * @author leeyueyang
 */

public class Parser {

    /**
     *
     * @param input String to be manipulated
     * @param argument argument to search for, typically either /by or /at
     * @return String found after the argument
     */
    private static String findDatetime(String input, String argument) {
        int argumentIndex = input.lastIndexOf(argument);
        String output = input.substring(argumentIndex + argument.length());

        if (output.replaceAll("\\s", "").length() < 1) {
            System.out.println("Please enter a valid date or time");
            return null;
        }

        return output.stripLeading().stripTrailing();
    }

    /**
     *
     * @param input String to be manipulated
     * @return Datetime String for switchblade.Deadline type Tasks
     */
    public static String findDeadlineDatetime(String input) {
        // split by /at and /to
        return findDatetime(input, "/by");
    }

    /**
     *
     * @param input String to be manipulated
     * @return String[] containing start datetime and end datetime
     */
    public static String[] findEventDatetime(String input) {
        // split by /at and /to
        String output = input;

        if (input.contains("/at")) {
            output = findDatetime(input, "/at");
        }

        if (output != null) {
            // first element would be the start datetime
            // second element would be the end datetime

            String[] outputArr = output.split("/to");

            for (int i = 0; i < outputArr.length; i++) {
                outputArr[i] = outputArr[i].stripLeading().stripTrailing();
            }
            return outputArr;
        }

        return null;
    }

    /**
     *
     * @param input String to be manipulated
     * @return Removes command from input string and returns description without datetime
     */
    public static String findDescription(String input) {
        String noCommand = input.split("\\s+", 2)[1];

        if (noCommand.contains("/by")) {
            return noCommand.split("/by")[0].stripLeading().stripTrailing();
        } else if (noCommand.contains("/at")) {
            return noCommand.split("/at")[0].stripLeading().stripTrailing();
        } else {
            return noCommand;
        }
    }
}
