package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindCommand extends Command{

    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword given by user to return tasks that contains keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    private TaskList findMatchingTasks(TaskList taskList) {
        Stream<Task> fullTaskStream = new ArrayList<>(taskList.getTaskList()).stream();
        BiFunction<Task, String, Boolean> hasKeyword = (task, keyword) -> {
            // return tasks that contains keyword
            return task.getTaskDescription().contains(keyword);
        };
        List<Task> findResults = fullTaskStream.filter(task ->
                hasKeyword.apply(task, this.keyword))
                .collect(Collectors.toList());

        return new TaskList(findResults);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        // Stream<Task> fullTaskStream = new ArrayList<Task>(taskList.getTaskList()).stream();
        // BiFunction<Task, String, Boolean> hasKeyword = (task, keyword) -> {
        //     // return tasks that contains keyword
        //     return task.getTaskDescription().contains(keyword);
        // };
        //
        // List<Task> findResults = fullTaskStream.filter(task -> {
        //         return hasKeyword.apply(task, this.keyword);
        //         }).collect(Collectors.toList());

        TaskList findResults = findMatchingTasks(taskList);
        ui.printFoundTasks(findResults);


    }


}
