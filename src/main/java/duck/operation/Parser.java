package duck.operation;

public class Parser {
    /**
     * Recognize the full command and divide it into command and descriptions
     *
     * @param fullCommand
     * @return Command class
     */
    public static CommandGui parse(String fullCommand) {
        String[] commandSplit = fullCommand.split(" ");
        String command;
        String description;
        try {
            command = commandSplit[0];
            description = fullCommand.substring(commandSplit[0].length() + 1, fullCommand.length()).trim();
            if (description.isBlank()) {
                description = null;
            }
        } catch (StringIndexOutOfBoundsException e) {
            command = commandSplit[0];
            description = null;
        } catch (ArrayIndexOutOfBoundsException e) {
            command = "null";
            description = null;
        }
        assert (!description.isBlank());
        return new CommandGui(command, description);
    }
}
