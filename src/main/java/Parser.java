/**
 * Parses the input from user to a format used by rest of the files.
 */
public class Parser {
    public static String trimWhiteSpaces(String input) {
        return input.trim();
    }

    /**
     * Returns a String array with the zero index being the first group of characters which are not separated by
     * whitespace, and the first index being the rest of the characters in str.
     *
     * @param input input by the user.
     * @return String[] zero index being the first group of characters which are not separated by
     * whitespace, and the first index being the rest of the characters in str.
     */
    public static String[] splitFirstAndRest(String input) {
        String[] result = new String[2];
        String[] split = input.split(" ");
        result[0] = split[0];
        String rest = "";
        for (int i = 1; i < split.length; i++) {
            rest += " " + split[i];
        }
        result[1] = rest;
        return result;
    }

    /**
     * Returns the parsed str as an integer.
     *
     * @param input input to be parsed to become an int.
     * @return int parsed str to an integer.
     * @throws NumberFormatException if str is not a number.
     */
    public static int makeToInt(String input) {
        try {
            return Integer.parseInt(Parser.trimWhiteSpaces(input));
        } catch (NumberFormatException e) {
            throw new NumberFormatException(Ui.lineGetter()
                     + " Enter a number after command, 'done (number)'\n or 'delete (number)'\n"
                     + Ui.lineGetter());
        }
    }

    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        }

        String[] split = Parser.splitFirstAndRest(input);
        String firstPartOfCommand = split[0];
        String secondPartOfCommand = split[1];

        if (firstPartOfCommand.equals("done")) {
            return new DoneCommand(secondPartOfCommand);
        } else if (firstPartOfCommand.equals("delete")) {
            return new DeleteCommand(secondPartOfCommand);
        } else if (firstPartOfCommand.equals("find")) {
            return new FindCommand(secondPartOfCommand);
        } else if (firstPartOfCommand.equals("todo") || firstPartOfCommand.equals("deadline")
                || firstPartOfCommand.equals("event")) {
            return new WriteCommand(firstPartOfCommand, secondPartOfCommand);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
