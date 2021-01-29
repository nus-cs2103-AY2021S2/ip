package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Searches (case-sensitive) for tasks containing a search term.
 */
public class FindCommand extends Command {
    private static final Pattern FIND_KEYWORD = Pattern.compile("(?i)find\\b");
    private static final Pattern FIND_TERM = Pattern.compile("(?i)find\\s+(\\w.*)");

    private final String term;

    private FindCommand(String term) {
        this.term = term;
    }

    /**
     * Returns true if the input string starts with the keyword find. False otherwise.
     *
     * @param input Command as a string.
     * @return True if the input string starts with the keyword find. False otherwise.
     */
    public static boolean isFindCommand(String input) {
        return FIND_KEYWORD.matcher(input).find();
    }

    /**
     * Parses the input command string as a find command and returns a FindCommand if successful.
     * Throws a DukeException with a relevant message if parsing fails.
     *
     * @param input Command as a string.
     * @return The parsed FindCommand.
     * @throws DukeException If parsing fails.
     */
    public static FindCommand parseFindCommand(String input) throws DukeException {
        Matcher findMatcher = FIND_TERM.matcher(input);
        if (!findMatcher.find()) {
            // Matched command but invalid argument
            throw new DukeException("The search term of a find command cannot be empty!\n"
                    + "Expected format: find <SEARCH TERM>");
        }

        String term = findMatcher.group(1);

        return new FindCommand(term);
    }

    /**
     * Returns a CommandResult with the matching tasks as a message for the user.
     *
     * @param tasks   The task list to search.
     * @param storage The storage used by Duke (unused).
     * @return A CommandResult with the matching tasks as a message for the user.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        StringBuilder feedback = new StringBuilder("Here are the matching tasks in your list:\n");
        int matchCount = 0;

        for (int i = 0; i < tasks.taskCount(); i++) {
            Task t = tasks.getAt(i);
            if (t.getDescription().contains(term)) {
                matchCount++;
                int index = i + 1;
                feedback.append(String.format("%d.%s\n", index, t));
            }
        }

        if (matchCount == 0) {
            feedback.setLength(0);
            feedback.append("No matching tasks found!");
        } else {
            // delete last newline
            feedback.deleteCharAt(feedback.length() - 1);
        }

        return new CommandResult(feedback.toString());
    }
}
