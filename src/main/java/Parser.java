public class Parser {

    public static Command parseCommand(String userInputCommand) throws InvalidCommandException {
        try {
            return Command.valueOf(userInputCommand.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
