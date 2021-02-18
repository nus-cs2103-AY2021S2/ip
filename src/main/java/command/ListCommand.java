package command;

import task.Task;
import task.TaskManager;
import util.Formatter;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ListCommand extends Command {
    public static final String COMMAND_STRING = "list";
    public static final CommandType COMMAND_TYPE = CommandType.LIST;

    public static ListCommand fromCommandMap(HashMap<String, List<String>> commandMap) {
        return new ListCommand();
    }

    @Override
    public String execute(TaskManager taskManager) {
        StringBuilder output = new StringBuilder();
        List<Task> tasks = taskManager.getTasks();
        List<String> taskStrings = tasks.stream()
                .map(Task::toString)
                .collect(Collectors.toList());
        String listOfTasks = Formatter.formatList(taskStrings);

        output.append("Here is your list of tasks: ").append("\n");
        output.append(listOfTasks);
        return output.toString();
    }
}
