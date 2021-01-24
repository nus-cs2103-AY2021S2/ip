public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    private static final String HELP_MESSAGE = "Here are the list of available commands:\n" +
            "BYE:\nExit the program\nUsage: bye\n" +
            "LIST:\nPrint the list of current tasks\nUsage: list\n" +
            "DONE:\nMark a task as completed\nUsage: done <task_number>\n" +
            "DELETE:\nDelete a task\nUsage: delete <task_number>\n" +
            "TODO:\nAdd a todo task\nUsage: todo <task_description>\n" +
            "DEADLINE:\nAdd a deadline task\nUsage: deadline <task_description> /by dd/mm/yyyy HHHH\n" +
            "EVENT:\nAdd an event task\nUsage: event <task_description> /at <event_time>\n" +
            "HELP:\nPrint available commands\nUsage: help";

    public HelpCommand() {
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(HELP_MESSAGE);
    }
}
