package duke.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.CallbackFunction;
import duke.Helper;
import duke.TaskList;
import duke.task.Task;
import javafx.util.Pair;

public class FindCommand extends Command {

    /**
     * Instantiates a new FindCommand object.
     * @param commandSplit user command split by spaces.
     */
    public FindCommand(String[] commandSplit) {
        super(commandSplit);
        assert commandSplit.length >= 1 && commandSplit[0].equals("find") : "Must have find keyword.";
    }

    /**
     * Filters the task list for tasks which descriptions contain the keyword provided by user.
     * @param list the task list.
     * @return The list of tasks containing the keyword, in String.
     */
    @Override
    public Pair<String, CallbackFunction> execute(TaskList list) {
        String keyword = Helper.join(this.commandSplit, 1);
        ArrayList<Task> filteredList = list.filter(task -> task.isDescriptionContainsString(keyword));
        //Convert each task to String then collect them into a List.
        List<String> tasksAsString = filteredList.stream().map(Task::toString).collect(Collectors.toList());
        String header = "Found " + tasksAsString.size() + " matching task(s):";
        //Add response header to front of tasks.
        tasksAsString.add(0, header);
        return new Pair<>(Helper.formatStrings(tasksAsString), CallbackFunction.empty());
    }
}
