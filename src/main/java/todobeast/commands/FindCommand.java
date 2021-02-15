package todobeast.commands;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.ToDoBeastException;
import todobeast.tasks.Task;

public class FindCommand extends Command {

    private final String regex;

    public FindCommand(String regex) {
        this.regex = regex;
    }

    public void execute(TaskList taskList, Ui ui) throws ToDoBeastException {
        if (taskList == null) {
            throw new ToDoBeastException("Task list cannot be found.");
        }
        List<Task> tasks = taskList.getTaskList();
//        List<Task> filteredList = tasks.stream()
//                .filter(task -> task.containsStringInDesc(regex))
//                .collect(Collectors.toList());
        Map<Integer, Task> filteredMap = tasks.stream()
                .filter(task -> task.containsStringInDesc(regex))
                .collect(Collectors.toMap(task -> taskList.getTaskList().indexOf(task), task -> task));
        ui.addToResponseOutput(ui.findTasks());
        ui.addToResponseOutput(ui.printFilteredMap(filteredMap));
    }
}
