package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Helper;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {

    public FindCommand(String[] commandSplit) {
        super(commandSplit);
    }

    /**
     * Filters the task list for tasks which descriptions contain the keyword provided by user.
     * @param list the task list.
     */
    @Override
    public void execute(TaskList list) {
        String keyword = Helper.join(this.commandSplit, 1);
        ArrayList<Task> filteredList = list.filter(task -> task.isDescriptionContainsString(keyword));
        List<String> tasksAsString = filteredList.stream().map(Task::toString).collect(Collectors.toList());
        tasksAsString.add(0, "Found " + tasksAsString.size() + " matching task(s):");
        Ui.printWithStyle(tasksAsString);
    }
}
