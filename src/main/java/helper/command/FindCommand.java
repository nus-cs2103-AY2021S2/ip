package helper.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.Task;

public class FindCommand extends Command {

    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    private List<Task> filterTasks(List<Task> listTask) {
        return listTask.stream().filter(t -> t.getDescription().contains(toFind))
                .collect(Collectors.toList());
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> cloneList = new ArrayList<>(tasks.getTaskList());
        return ui.listOfTaskToString(filterTasks(cloneList));
    }
}
