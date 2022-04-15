package command;

import task.Task;
import task.TaskManager;
import util.DukeException;
import util.Formatter;
import util.Parser;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Command to search for keywords in the description of Tasks in a supplied
 * TaskManager and return a list of the matching Tasks.
 */
public class FindCommand extends Command {
    public static final String COMMAND_STRING = "find";
    public static final CommandType COMMAND_TYPE = CommandType.FIND;
    private final List<String> keywords;

    /**
     * Creates a FindCommand that would return a String that lists all the Tasks
     * whose descriptions match all the keywords.
     *
     * @param keywords List of keywords that must appear in the matching Tasks.
     */
    private FindCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Constructs a FindCommand from a commandMap.
     *
     * @param commandMap CommandMap representing the instruction.
     * @return FindCommand object based on the commandMap.
     * @throws DukeException When the user does not specify any search keywords.
     */
    public static FindCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        assert Parser.extractCommandString(commandMap).equals(COMMAND_STRING)
                : COMMAND_STRING + "CommandFlag does not match";

        List<String> keywords = commandMap.get(COMMAND_STRING);

        if (keywords.isEmpty()) {
            throw new DukeException("Please include at least one keyword.");
        }

        return new FindCommand(keywords);
    }

    /**
     * Searches the supplied TaskManager for all Tasks whose descriptions contain
     * all of the keywords.
     *
     * @param taskManager TaskManager object to search in.
     * @return String that lists all the matching tasks.
     */
    @Override
    public String execute(TaskManager taskManager) {
        StringBuilder output = new StringBuilder();

        // Returns true if the task contains all of the keywords
        Predicate<Task> taskPredicate = task -> {
            String taskDescription = task.getDescription();
            for (String k : keywords) {
                if (!taskDescription.contains(k)) {
                    return false;
                }
            }
            return true;
        };

        List<Task> tasks = taskManager.getTasks();
        List<String> filteredTaskStrings = tasks.stream()
                .filter(taskPredicate)
                .map(Task::toString)
                .collect(Collectors.toList());

        // If no tasks match the predicate, inform the user.
        if (filteredTaskStrings.isEmpty()) {
            String quotedKeywords = keywords.stream()
                    .map(s -> "\"" + s + "\"")
                    .collect(Collectors.joining(" + "));
            return "No tasks match the keywords: \n" + quotedKeywords;
        }

        String listOfTasks = Formatter.formatList(filteredTaskStrings);
        output.append("Here are the tasks that match your search: ").append("\n");
        output.append(listOfTasks);

        return output.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindCommand that = (FindCommand) o;
        return keywords.equals(that.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywords);
    }
}
