package duke.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.Helper;
import duke.TaskList;
import duke.task.Task;

public class FindCommand extends Command {

    public FindCommand(String[] commandSplit) {
        super(commandSplit);
        assert commandSplit.length >= 1 && commandSplit[0].equals("find"): "Must have find keyword.";
    }

    /**
     * Filters the task list for tasks which descriptions contain the keyword provided by user.
     * @param list the task list.
     */
    @Override
    public String execute(TaskList list) {
        String keyword = Helper.join(this.commandSplit, 1);
        ArrayList<Task> filteredList = list.filter(task -> task.isDescriptionContainsString(keyword));
        List<String> tasksAsString = filteredList.stream().map(Task::toString).collect(Collectors.toList());
        tasksAsString.add(0, "Found " + tasksAsString.size() + " matching task(s):");
        return Helper.formatStrings(tasksAsString.toArray(new String[0]));
    }
}
