import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddToDoCommand extends Command {
    private static final Pattern ADD_TODO_KEYWORD = Pattern.compile("(?i)todo\\b");
    private static final Pattern ADD_TODO_DESC = Pattern.compile("(?i)todo\\s+(\\w.*)");

    private final String taskDesc;

    private AddToDoCommand(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public static boolean isAddToDoCommand(String input) {
        return ADD_TODO_KEYWORD.matcher(input).find();
    }

    public static AddToDoCommand parseAddToDoCommand(String input) throws DukeException {
        Matcher toDoMatcher = ADD_TODO_DESC.matcher(input);
        if (!toDoMatcher.find()) {
            // Matched command but invalid argument
            throw new DukeException("The description of a todo cannot be empty!\n"
                    + "Expected format: todo <DESCRIPTION>");
        }

        String taskDesc = toDoMatcher.group(1);

        return new AddToDoCommand(taskDesc);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        ToDo toDo = new ToDo(taskDesc);
        tasks.add(toDo);
        String feedback = String.format("Got it. I've added this task:\n"
                        + "%s\n"
                        + "Now you have %d task(s) in the list.",
                toDo.toString(), tasks.taskCount());
        return new CommandResult(feedback);
    }
}
