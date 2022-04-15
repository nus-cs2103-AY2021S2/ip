import java.io.IOException;

public class Parser {

    /**
     * Parses the user command and instructs the taskList, ui or sc
     * to carry out different functions according to the command argument.
     *
     * @param command a String representing user input.
     * @param commandHandler a CommandHandler object to handle the response to user input.
     * @return a String representing Maya's response to the user command.
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
            return parseTaskCommand("todo", command, commandHandler);
        case "deadline":
            return parseTaskCommand("deadline", command, commandHandler);
        case "event":
            return parseTaskCommand("event", command, commandHandler);
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

    /**
     * Returns the first word of the user input.
     *
     * @param command a String representing the user input.
     * @return a String for the first word of the user input.
     */
    static String parseCommand(String command) {
        return command.split(" ", 2)[0];
    }

    /**
     * Returns the user input from the second word to the end.
     *
     * @param command a String representing the user input.
     * @return a String the user input from the second word to the end.
     */
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

    /**
     * Splits a given text once by a given regex.
     *
     * @param text a String representing the text to be split.
     * @param regex a String representing how the text should be split.
     * @return a String array with 2 members after splitting.
     * @throws CommandFormatException if the length of the resulting array is less than 2.
     */
    static String[] splitByRegex(String text, String regex) throws CommandFormatException {
        String[] splitArr = text.split(regex, 2);

        if (splitArr.length < 2) {
            throw new CommandFormatException();
        }

        return splitArr;
    }

    /**
     * Parses the task commands into name, priority and time.
     *
     * @param taskType a String representing the type of Task (todo, deadline or event).
     * @param command a String representing the user command.
     * @param commandHandler a commandHandler object.
     * @return a String representing Maya's response to the task command.
     */
    static String parseTaskCommand(String taskType, String command, CommandHandler commandHandler)
            throws CommandFormatException, IOException {
        String description = getDescription(command);
        String name;
        String priority;
        String time;

        if (taskType.equals("todo")) {
            String[] splitByPriorityArr = splitByRegex(description, "/p");
            name = splitByPriorityArr[0].trim();
            priority = splitByPriorityArr[1].trim();
            time = "";
        } else {
            String regex;
            if (taskType.equals("deadline")) {
                regex = "/by";
            } else {
                regex = "/at";
            }

            String[] splitTimeArr = splitByRegex(description, regex);
            String[] splitByPriorityArr = splitByRegex(splitTimeArr[1], "/p");

            name = splitTimeArr[0].trim();
            priority = splitByPriorityArr[1].trim();
            time = splitByPriorityArr[0].trim();
        }

        return commandHandler.handleTaskCommand(taskType, name, priority, time);
    }
}
