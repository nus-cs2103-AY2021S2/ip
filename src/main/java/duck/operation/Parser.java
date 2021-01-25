package duck.operation;

import duck.operation.Command;

public class Parser {
    public static Command parse(String fullCommand) {
        String[] commandSplit = fullCommand.split(" ");
        String description;
        try {
            description = fullCommand.substring(commandSplit[0].length() + 1, fullCommand.length()).trim();
        } catch (StringIndexOutOfBoundsException e) {
            description = null;
        }
        return new Command(commandSplit[0], description);
    }
}
