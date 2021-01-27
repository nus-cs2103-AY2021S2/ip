import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCommand extends Command {
    private static final Pattern LIST_KEYWORD = Pattern.compile("(?i)list\\b");
    private static final Pattern LIST_ONLY = Pattern.compile("(?i)list(\\s*)$");

    private ListCommand() {
    }

    public static boolean isListCommand(String input) {
        return LIST_KEYWORD.matcher(input).find();
    }

    public static ListCommand parseListCommand(String input) throws DukeException {
        Matcher listMatcher = LIST_ONLY.matcher(input);
        if (!listMatcher.find()) {
            throw new DukeException("Please do not include any arguments after the list command.\n"
                    + "Expected format: list");
        }

        return new ListCommand();
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        StringBuilder feedback = new StringBuilder();

        if (tasks.taskCount() == 0) {
            feedback.append("No tasks currently!");
        } else {
            for (int i = 0; i < tasks.taskCount(); i++) {
                Task t = tasks.getAt(i);
                int index = i + 1;
                feedback.append(String.format("%d.%s", index, t));
                if (i < tasks.taskCount() - 1) {
                    feedback.append("\n");
                }
            }
        }

        return new CommandResult(feedback.toString());
    }
}
