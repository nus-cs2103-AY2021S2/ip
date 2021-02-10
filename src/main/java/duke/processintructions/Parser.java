package duke.processintructions;


public class Parser {

    /**
     * Converts the string of the name of the command into an enumerated command.
     * An erroneous command will still be converted into a command, specifically the ERROR command.
     *
     * @param input String name of command.
     * @return Command object correlated to the name.
     */
    public static Command parse(String input) {
        Command currentCommand;
        try {
            currentCommand = Command.valueOf(input.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            currentCommand = Command.ERROR;
        }
        return currentCommand;
    }
}
