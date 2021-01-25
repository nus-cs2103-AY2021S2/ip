public class Parser {
    private static String findDatetime(String input, String argument) {
        int argumentIndex = input.lastIndexOf(argument);
        String output = input.substring(argumentIndex + argument.length());

        if (output.replaceAll("\\s", "").length() < 1) {
            System.out.println("Please enter a valid date or time");
            return null;
        }

        return output.stripLeading().stripTrailing();
    }

    public static String findDeadlineDatetime(String input) {
        // split by /at and /to
        return findDatetime(input, "/by");
    }

    public static String[] findEventDatetime(String input) {
        // split by /at and /to
        String output = input;

        if (input.contains("/at"))
            output = findDatetime(input, "/at");

        if (output != null) {
            // first element would be the start datetime
            // second element would be the end datetime

            String[] outputArr = output.split("/to");
//            System.out.println(outputArr[0]);

            for (int i = 0; i < outputArr.length; i++)
                outputArr[i] =  outputArr[i].stripLeading().stripTrailing();
            return outputArr;
        }

        return null;
    }

    public static String findDescription(String input) {
        String noCommand = input.split("\\s+", 2)[1];

        if (noCommand.contains("/by"))
            return noCommand.split("/by")[0];
        else if (noCommand.contains("/at"))
            return noCommand.split("/at")[0];
        else
            return null;
    }
}
