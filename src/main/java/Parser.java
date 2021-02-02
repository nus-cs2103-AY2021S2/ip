public class Parser {
    public static Command parseCommand(String userInput) throws DukeException {
        userInput = userInput.trim();
        checkValidInput(userInput);
        String[] userInputArr = userInput.split(" ", 2);
        CommandType commandType = CommandType.valueOfLabel(userInputArr[0]);

        switch (commandType) {
        case TODO:
            return new TodoCommand(userInputArr[1]);
        case DEADLINE:
            return new DeadlineCommand(userInputArr[1]);
        case EVENT:
            return new EventCommand(userInputArr[1]);
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(userInputArr[1]);
        case DELETE:
            return new DeleteCommand(userInputArr[1]);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    private static void checkValidInput(String userInput) throws DukeException {

    }
}
