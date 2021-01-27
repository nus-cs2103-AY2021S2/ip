package duke.commands;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDeadlineCommand extends Command {
    private static final Pattern ADD_DEADLINE_KEYWORD = Pattern.compile("(?i)deadline\\b");
    private static final Pattern ADD_DEADLINE_DESC = Pattern.compile("(?i)deadline\\s+(\\w.*)");
    private static final Pattern ADD_DEADLINE_DATE = Pattern.compile("(?i)deadline\\s+(\\w.*)\\s+/by\\s+(\\w.*)");

    private final String taskDesc;
    private final LocalDate date;

    private AddDeadlineCommand(String taskDesc, LocalDate date) {
        this.taskDesc = taskDesc;
        this.date = date;
    }

    public static boolean isAddDeadlineCommand(String input) {
        return ADD_DEADLINE_KEYWORD.matcher(input).find();
    }

    public static AddDeadlineCommand parseAddDeadlineCommand(String input) throws DukeException {
        // check description exists
        Matcher descMatcher = ADD_DEADLINE_DESC.matcher(input);
        if (!descMatcher.find()) {
            throw new DukeException("The description of a deadline cannot be empty!\n"
                    + "Expected format: deadline <DESCRIPTION> /by <DATE>");
        }

        // check date exists
        Matcher dateMatcher = ADD_DEADLINE_DATE.matcher(input);
        if (!dateMatcher.find()) {
            throw new DukeException("A deadline must have a date!\n"
                    + "Expected format: deadline <DESCRIPTION> /by <DATE>");
        }

        String taskDesc = dateMatcher.group(1);
        String dateStr = dateMatcher.group(2);

        try {
            LocalDate date = LocalDate.parse(dateStr, Deadline.INPUT_DATE_FORMATTER);
            return new AddDeadlineCommand(taskDesc, date);
        } catch (DateTimeParseException ex) {
            throw new DukeException(String.format("Sorry, I don't recognize this date: '%s'\n"
                    + "Use this format please: %s", dateStr, Deadline.INPUT_DATE_FORMAT));
        }
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        Deadline deadline = new Deadline(taskDesc, date);
        tasks.add(deadline);
        String feedback = String.format("Got it. I've added this task:\n"
                        + "%s\n"
                        + "Now you have %d task(s) in the list.",
                deadline.toString(), tasks.taskCount());
        return new CommandResult(feedback);
    }
}
