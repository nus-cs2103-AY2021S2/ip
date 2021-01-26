public class Parser {

    public static Command parse(String inputString) throws DukeException {
        String[] splitInput = inputString.split(" ", 2);

        Command c;
        if (splitInput.length < 2) {
            splitInput = new String[]{splitInput[0], ""};
        }
        String argument = splitInput[1].trim();

        CommandType commandType = CommandType.valueOf(splitInput[0].trim()
                .toUpperCase());

        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand(argument);
        case DONE:
            return new DoneCommand(argument);
        case TODO:
            return new TodoCommand(argument);
        case DEADLINE:
            return new DeadlineCommand(argument);
        case EVENT:
            return new EventCommand(argument);
        case DELETE:
            return new DeleteCommand(argument);
        default:
            throw new DukeException("Sorry, I don't quite understand!");
        }
    }
}
