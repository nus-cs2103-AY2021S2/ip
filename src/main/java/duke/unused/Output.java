package duke.unused;

public abstract class Output {

    private String horizontalLine() {
        return "    ____________________________________________________________";
    }

    private String indentedString(String input) {
        return "     " + input;
    }

    public String defaultFormatting(String input) {
        return horizontalLine() + '\n' + indentedString(input) + '\n' + horizontalLine();
    }

    /**
     * pad a string with spaces at its front
     * @param input input string
     * @param numOfSpacesToPad number of spaces to add in front of a string
     * @return string padded with spaces
     */
    public String padSpaces(String input, int numOfSpacesToPad) {
        String toreturn = "";

        for (int i = 0; i < numOfSpacesToPad; i++) {
            toreturn = toreturn + ' ';
        }

        return toreturn + input;
    }
}
