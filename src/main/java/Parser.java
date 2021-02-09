public class Parser {
    public static Command parseCommand(String userInput) throws DukeException {
        String[] commandArr = userInput.split(" ", 2);
        String commandType = commandArr[0];

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
        } else if (commandType.equals("deadline")) {
            if (commandArr.length == 1) {
                throw new DukeException("Oops! The description of deadline cannot be empty!");
            } else {
                String commandDescription = commandArr[1];
                String[] descriptionArr = commandDescription.split("/by ");
                return new DeadlineCommand(descriptionArr[0], descriptionArr[1]);
            }
        } else if (commandType.equals("event")) {
            if (commandArr.length == 1) {
                throw new DukeException("Oops! The description of event cannot be empty!");
            } else {
                String commandDescription = commandArr[1];
                String[] descriptionArr = commandDescription.split("/at ");
                return new EventCommand(descriptionArr[0], descriptionArr[1]);
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
