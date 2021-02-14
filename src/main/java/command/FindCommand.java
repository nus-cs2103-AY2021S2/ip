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
    private String message = "";

    private FindCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    public static FindCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        if (!commandMap.containsKey(COMMAND_STRING)) {
            throw new DukeException("Please include at least one keyword.");
        }

        List<String> keywords = commandMap.get(COMMAND_STRING);
        return new FindCommand(keywords);
    }

    @Override
    public void execute(TaskManager taskManager) throws DukeException {
        StringBuilder output = new StringBuilder();

        // Returns true if the task contains at least one of the keywords
        Predicate<Task> taskPredicate = task -> {
            String taskDescription = task.getDescription();
            for (String k: keywords) {
                if (taskDescription.contains(k)) {
                    return true;
                }
            }
            return false;
        };

        List<Task> tasks = taskManager.getTasks();
        List<String> filteredTaskStrings = tasks.stream()
                .filter(taskPredicate)
                .map(Task::toString)
                .collect(Collectors.toList());
        String listOfTasks = Formatter.formatList(filteredTaskStrings);

        output.append("Here are the tasks that match your search: ").append("\n");
        output.append(listOfTasks);
        message = output.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
