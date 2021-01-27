public class Parser {
    public static String trimWhiteSpaces(String str) {
        return str.trim();
    }

    public static String[] firstAndRest(String str) {
        String[] res = new String[2];
        String[] split = str.split(" ");
        res[0] = split[0];
        String rest = "";
        for(int i = 1; i < split.length; i++) {
            rest += " " + split[i];
        }
        res[1] = rest;
        return res;
    }

    public static int makeToInt(String str) {
        try {
            return Integer.parseInt(Parser.trimWhiteSpaces(str));
        } catch (NumberFormatException e) {
            throw new NumberFormatException(Ui.lineGetter() +
                    " Enter a number after command, 'done (number)' or 'delete (number)'\n"
                     + Ui.lineGetter());
        }
    }
}
