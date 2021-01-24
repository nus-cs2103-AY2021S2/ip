public class Parser {

    public Command parse(String input) {
        String[] splitInput = input.split(" ", 2);
        String uncheckedCommand = splitInput[0].toUpperCase();
        String details = "For those without details";
        if (splitInput.length > 1) {
            details = splitInput[1];
        }
        Input command = checkCommand(uncheckedCommand);
        switch(command) {
        case LIST:
            return new ListCommand(details);
        case TODO:
            return new TodoCommand(details);
        case DEADLINE:
            return new DeadlineCommand(details);
        case EVENT:
            return new EventCommand(details);
        case DONE:
            return new DoneCommand(details);
        case BYE:
            return new ByeCommand(details);
        default:
            return new ErrorCommand(details);
        }
    }

    private Input checkCommand(String command) {
        try {
            Input inputCommand = Input.valueOf(command);
            return inputCommand;
        } catch (IllegalArgumentException e) {
            return Input.ERROR;
        }
    }
}
