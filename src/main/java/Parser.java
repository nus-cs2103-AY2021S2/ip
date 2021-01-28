public class Parser {
    public static String trimWhiteSpaces(String input) {
        return input.trim();
    }

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

    public static int makeToInt(String input) {
        try {
            return Integer.parseInt(Parser.trimWhiteSpaces(input));
        } catch (NumberFormatException e) {
            throw new NumberFormatException(Ui.lineGetter() +
                    " Enter a number after command, 'done (number)'\n or 'delete (number)'\n"
                     + Ui.lineGetter());
        }
    }
}
