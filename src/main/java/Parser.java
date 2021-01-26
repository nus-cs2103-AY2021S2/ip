public class Parser {

    public static Command parse(String input) {
        String[] splitInput = input.split(" ", 2);
        splitInput[0] = splitInput[0].toUpperCase();
        Input command = checkCommand(splitInput[0]);
        splitInput[0] = command.name();
        System.out.println("AAA");
        switch(command) {
        case LIST:
            return new ListCommand(splitInput);
        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(splitInput);
        case DELETE:
        case DONE:
            return new EditCommand(splitInput);
        case BYE:
            return new ExitCommand();
        default:
            return new HelpCommand(splitInput);
        }
    }

    private static Input checkCommand(String command) {
        try {
            return Input.valueOf(command);
        } catch (IllegalArgumentException e) {
            return Input.ERROR;
        }
    }

}
