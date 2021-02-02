public class Parser {
    public static Command parseCommand(String userInput) {
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
        } else if (commandType.equals("deadline")) {
            if (commandArr.length == 1) {
                System.out.println("Oops! The description of deadline cannot be empty!");
                return new InvalidTaskCommand();
            } else {
                String commandDescription = commandArr[1];
                String[] descriptionArr = commandDescription.split("/by ");
                return new DeadlineCommand(descriptionArr[0], descriptionArr[1]);
            }
        } else if (commandType.equals("event")) {
            if (commandArr.length == 1) {
                System.out.println("Oops! The description of event cannot be empty!");
                return new InvalidTaskCommand();
            } else {
                String commandDescription = commandArr[1];
                String[] descriptionArr = commandDescription.split("/at ");
                return new EventCommand(descriptionArr[0], descriptionArr[1]);
            }
        } else if (commandType.equals("todo")) {
            if (commandArr.length == 1) {
                System.out.println("Oops! The description of todo cannot be empty!");
                return new InvalidTaskCommand();
            } else {
                String commandDescription = commandArr[1];
                return new TodoCommand(commandDescription);
            }
        } else {
            return new InvalidTaskCommand();
        }
    }
}
