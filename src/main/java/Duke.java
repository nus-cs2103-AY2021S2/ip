import java.util.List;

public class Duke {

    /**
     * List of tasks added by the user
     */
    private static final List<Task> tasks = Storage.getData();
    private static String lastCommand;
    private static Task deletedTask = new Task("dummy");

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
            lastCommand = command;
            reply = TaskList.markDone(command, tasks);
        } else if (parsedCommand.equals("todo")) {
            try {
                reply = TaskList.addTodo(command, tasks);
                lastCommand = command;
            } catch (InvalidTodoException e) {
                reply = Ui.getEmptyTodoMessage();
            }
        } else if (parsedCommand.equals("deadline")) {
            try {
                reply = TaskList.addDeadline(command, tasks);
                lastCommand = command;
            } catch (InvalidDeadlineException e) {
                reply = Ui.getInvalidDeadlineMessage();
            } catch (InvalidDateTimeFormatException e) {
                reply = Ui.getInvalidDateFormatMessage();
            }
        } else if (parsedCommand.equals("event")) {
            try {
                reply = TaskList.addEvent(command, tasks);
                lastCommand = command;
            } catch (InvalidEventException e) {
                reply = Ui.getInvalidEventMessage();
            } catch (InvalidDateTimeFormatException e) {
                reply = Ui.getInvalidDateFormatMessage();
            }
        } else if (parsedCommand.equals("delete")) {
            lastCommand = command;
            deletedTask = tasks.get(Integer.parseInt(command.split(" ")[1]) - 1);
            reply = TaskList.deleteTask(command, tasks);
        } else if (parsedCommand.equals("find")) {
            reply = TaskList.findTask(command, tasks);
        } else if (parsedCommand.equals("undo")) {
            reply = TaskList.undoCommand(tasks, lastCommand, deletedTask);
        } else if (command.equals("bye")) {
            reply = Ui.printExitMessage();
            System.exit(0);
        } else {
            throw new InvalidCommandException();
        }
        assert !reply.isEmpty() : "reply should not be an empty string";
        return reply;
    }

    public String getResponse(String input) {
        String reply;
        try {
            reply = runCommand(input);
        } catch (InvalidCommandException e) {
            reply = Ui.getInvalidCommandMessage();
        }
        assert !reply.isEmpty() : "reply should not be an empty string";
        return reply;
    }

}
