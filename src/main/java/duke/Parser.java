package duke;

/**
 * Provides the static method for parsing user input.
 */
public class Parser {

    /**
     * Handles the logic of parsing user input to determine type of command.
     * The rest of the user input is parsed according to command type to pass
     * correct arguments to create a command to execute.
     *
     * @param userInput the input given by the user to Duke.
     * @return the command to be executed.
     * @throws DukeException if user input is invalid or if description of tasks is empty.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        assert userInput != null;
        String[] commandArr = userInput.split(" ", 2);
        String commandType = commandArr[0].toLowerCase();

        if (commandType.equals("bye")) {
            return new ExitCommand();
        } else if (commandType.equals("list")) {
            return new ListCommand();
        } else if (commandType.equals("done")) {
            int taskIndex = Integer.parseInt(commandArr[1]) - 1;
            return new DoneCommand(taskIndex);
        } else if (commandType.equals("delete")) {
            int taskIndex = Integer.parseInt(commandArr[1]) - 1;
            return new DeleteCommand(taskIndex);
        } else if (commandType.equals("find")) {
            if (commandArr.length == 1) {
                throw new DukeException("Oops! Please provide a keyword you want to find!");
            } else {
                String keyword = commandArr[1];
                return new FindCommand(keyword);
            }
        } else if (commandType.equals("update")) {
            String commandDescription = commandArr[1];
            return new UpdateCommand(commandDescription);
        } else if (commandType.equals("deadline")) {
            if (commandArr.length == 1) {
                throw new DukeException("Oops! The description of deadline cannot be empty!");
            } else {
                String commandDescription = commandArr[1];
                return new DeadlineCommand(commandDescription);
            }
        } else if (commandType.equals("event")) {
            if (commandArr.length == 1) {
                throw new DukeException("Oops! The description of event cannot be empty!");
            } else {
                String commandDescription = commandArr[1];
                return new EventCommand(commandDescription);
            }
        } else if (commandType.equals("todo")) {
            if (commandArr.length == 1) {
                throw new DukeException("Oops! The description of todo cannot be empty!");
            } else {
                String commandDescription = commandArr[1];
                return new TodoCommand(commandDescription);
            }
        } else {
            return new InvalidTaskCommand();
        }
    }
}
