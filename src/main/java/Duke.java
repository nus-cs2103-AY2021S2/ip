import java.util.List;

public class Duke {

    /** List of tasks added by the user */
    private static final List<Task> tasks = Storage.getData();

    /**
     * Performs the specified action.
     *
     * @param command Command input by the user.
     * @return Reply to the user's command.
     * @throws InvalidCommandException If the command cannot be recognised.
     */
    public static String runCommand(String command) throws InvalidCommandException {
        String reply;
        String parsedCommand = Parser.parseCommand(command);
        if (command.equals("list")) {
            reply = TaskList.printList(tasks);
        } else if (parsedCommand.equals("done")) {
            int index = Parser.parseDoneIndex(command);
            reply = TaskList.markDone(index, tasks);
        } else if (parsedCommand.equals("todo")) {
            try {
                reply = TaskList.addTodo(command, tasks);
            } catch (InvalidTodoException e) {
                reply = Ui.printEmptyTodoMessage();
            }
        } else if (parsedCommand.equals("deadline")) {
            try {
                reply = TaskList.addDeadline(command, tasks);
            } catch (InvalidDateTimeFormatException e) {
                reply = Ui.printInvalidDateFormatMessage();
            }
        } else if (parsedCommand.equals("event")) {
            try {
                reply = TaskList.addEvent(command, tasks);
            } catch (InvalidDateTimeFormatException e) {
                reply = Ui.printInvalidDateFormatMessage();
            }
        } else if (parsedCommand.equals("delete")) {
            reply = TaskList.deleteTask(command, tasks);
        } else if (parsedCommand.equals("find")) {
            reply = TaskList.findTask(command, tasks);
        } else if (command.equals("bye")) {
            reply = Ui.printExitMessage();
            System.exit(0);
        } else {
            throw new InvalidCommandException();
        }
        return reply;
    }

    public String getResponse(String input) {
        String reply;
        try {
            reply = runCommand(input);
        } catch (InvalidCommandException e) {
            reply = Ui.printInvalidCommandMessage();
        }
        return reply;
    }

}
