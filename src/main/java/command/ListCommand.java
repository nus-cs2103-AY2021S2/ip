package command;

import task.Task;
import task.TaskManager;
import util.Formatter;
import util.Parser;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command to list out all Tasks in the supplied TaskManager.
 */
public class ListCommand extends Command {
    public static final String COMMAND_STRING = "list";
    public static final CommandType COMMAND_TYPE = CommandType.LIST;

    /**
     * Constructs a ListCommand from a commandMap.
     *
     * @param commandMap CommandMap representing the instruction.
     * @return ListCommand based on the commandMap.
     */
    public static ListCommand fromCommandMap(HashMap<String, List<String>> commandMap) {
        assert Parser.extractCommandString(commandMap).equals(COMMAND_STRING)
                : COMMAND_STRING + "CommandFlag does not match";

        return new ListCommand();
    }

    /**
     * Creates a String listing all the Tasks in the supplied TaskManager.
     *
     * @param taskManager TaskManager object to perform the action on.
     * @return String that lists all the tasks.
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
