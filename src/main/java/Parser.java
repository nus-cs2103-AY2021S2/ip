public class Parser {

    public static Command parse(String input) throws DukeException{
        String[] commandStr = input.trim().split("\\s+");
        String taskCommand = commandStr[0];
        Command commandType;

        switch (taskCommand) {
        case "bye":
            commandType = new ExitCommand(taskCommand);
            break;
        case "list":
            commandType = new ListCommand(taskCommand);
            break;
        case "done":
            commandType = new DoneCommand(taskCommand, Integer.parseInt(commandStr[1]));
            break;
        case "todo":
        case "deadline":
        case "event":
            commandType = new AddTask(taskCommand, input.replaceFirst(taskCommand, ""));
            break;
        case "delete":
            commandType = new DeleteTask(taskCommand, Integer.parseInt(commandStr[1]));
            break;
        default:
            throw new DukeException(ExceptionType.INVALID_INPUT, "");
        }
        return commandType;
    }
}