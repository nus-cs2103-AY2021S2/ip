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
        String description;
        try {
            description = fullCommand.substring(commandSplit[0].length() + 1, fullCommand.length()).trim();
        } catch (StringIndexOutOfBoundsException e) {
            description = null;
        }
        return new CommandGui(commandSplit[0], description);
    }
}
