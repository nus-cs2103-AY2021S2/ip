package duke.customClass;


public class Parser {

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
