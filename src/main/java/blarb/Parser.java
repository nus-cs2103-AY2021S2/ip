package blarb;

/**
 * {@code Parser} parses the inputted string.
 */
class Parser {
    /**
     * The inputted command line is parsed into a {@code CommandLine} object.
     *
     * @param input The input commandline.
     * @return The parsed {@code Commandline} object.
     */
    static CommandLine parse(String input) {
        String[] tokens = input.split(" ", 2);
        Command command = Command.command(tokens[0]);
        if (tokens.length == 1) {
            return new CommandLine(command);
        }
        return new CommandLine(command, tokens[1]);
    }
}
