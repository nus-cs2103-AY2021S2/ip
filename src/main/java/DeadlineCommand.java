import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("(.*)\\W(?=\\/by)\\/by\\W(.*)");
    private static Matcher matcher;

    public static final String COMMAND = "deadline";

    private String description;
    private LocalDateTime by;

    private DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute() throws DukeException {
        Task newTask = new Deadline(description, by);
        taskList.addTask(newTask);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n\t").append(newTask).append("\nNow you have ")
                .append(taskList.getSize());
        if (taskList.getSize() == 1) {
            builder.append(" task");
        } else {
            builder.append(" tasks");
        }
        return builder.toString();
    }

    @Override
    public boolean shouldSave() {
        return true;
    }

    public static DeadlineCommand parseArguments(String input) throws DukeException {
        matcher = COMMAND_FORMAT.matcher(input);

        if (!matcher.matches()) {
            throw new DukeException("☹ Sorry, please enter a valid description and datetime for the deadline\n\t" +
                    "Command: deadline [description] /by [deadline]");
        }

        String description = matcher.group(0);
        LocalDateTime by = DateParser.parseDateTime(matcher.group(1));

        return new DeadlineCommand(description, by);
    }
}
