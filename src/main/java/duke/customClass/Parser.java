package duke.customClass;


public class Parser {

    /**
     * Converts the string of the name of the command into an enumerated command.
     * @param input string name of command
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
