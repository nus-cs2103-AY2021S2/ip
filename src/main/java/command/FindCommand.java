package command;

import task.Task;
import task.TaskManager;
import util.DukeException;
import util.Formatter;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public static final String COMMAND_STRING = "find";
    public static final CommandType COMMAND_TYPE = CommandType.FIND;
    private final List<String> keywords;

    private FindCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    public static FindCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        List<String> keywords = commandMap.get(COMMAND_STRING);

        if (keywords.isEmpty()) {
            throw new DukeException("Please include at least one keyword.");
        }

        return new FindCommand(keywords);
    }

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
        String listOfTasks = Formatter.formatList(filteredTaskStrings);

        output.append("Here are the tasks that match your search: ").append("\n");
        output.append(listOfTasks);
        return output.toString();
    }
}
