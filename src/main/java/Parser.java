import java.io.IOException;

public class Parser {

    /**
     * Parses the user command and instructs the taskList, ui or sc
     * to carry out different functions according to the command argument.
     *
     * @param command user input
     * @throws MayaException if command is not supported by Maya.
     * @throws IOException if null is supplied to Storage or if file is not found.
     */
    static String parse(String command, CommandHandler commandHandler) throws MayaException, IOException {
        switch(parseCommand(command)) {
        case "bye":
            return commandHandler.handleByeCommand();
        case "list":
            return commandHandler.handleListCommand();
        case "done":
            // To get the index
            int index = Integer.parseInt(getDescription(command));
            return commandHandler.handleDoneCommand(index);
        case "todo":
            String todoName = getDescription(command);
            String[] todoSplitArr = splitByRegex(todoName, "/p");

            return commandHandler.handleTaskCommand("todo", todoSplitArr[0].trim(),
                    todoSplitArr[1].trim(), "");
        case "deadline":
            String deadlineName = getDescription(command);
            String[] deadlineBySplitArr = splitByRegex(deadlineName, "/by");
            String[] deadlinePrioritySplitArr = splitByRegex(deadlineBySplitArr[1], "/p");

            return  commandHandler.handleTaskCommand("deadline", deadlineBySplitArr[0].trim(),
                    deadlinePrioritySplitArr[1].trim(), deadlinePrioritySplitArr[0].trim());
        case "event":
            String eventName = getDescription(command);
            String[] eventBySplitArr = splitByRegex(eventName, "/at");
            String[] eventPrioritySplitArr = splitByRegex(eventBySplitArr[1], "/p");

            return  commandHandler.handleTaskCommand("event", eventBySplitArr[0].trim(),
                    eventPrioritySplitArr[1].trim(), eventPrioritySplitArr[0].trim());
        case "find":
            String searchString = getDescription(command);
            return commandHandler.handleFindCommand(searchString);
        case "delete":
            // To get the index
            int i = Integer.parseInt(getDescription(command));
            return commandHandler.handleDeleteCommand(i);
        default:
            throw new UnknownCommandException();
        }
    }

    static String parseCommand(String command) {
        return command.split(" ", 2)[0];
    }

    static String getDescription(String command) throws CommandFormatException {
        try {
            return command.split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            String task = parseCommand(command);
            if (task.equals("find")) {
                throw new CommandFormatException("☹ OOPS!!! The search keyword cannot be empty.");
            } else {
                throw new CommandFormatException("☹ OOPS!!! The description of " + task
                        + " cannot be empty.");
            }
        }
    }

    static String[] splitByRegex(String text, String regex) throws CommandFormatException {
        String[] splitArr = text.split(regex, 2);

        if (splitArr.length < 2) {
            throw new CommandFormatException();
        }

        return splitArr;
    }
}
