package checklst.task;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import checklst.exception.ChecklstException;

public class TaskList {
    
    private final List<Task> taskList;

    /**
     * Creates a new TaskList object. 
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    private TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String add(Task task) {
        this.taskList.add(task);
        return String.format("Added: %s\n\tYou now have %d task(s) in the list!", task, this.taskList.size());
    }
 
    public Task completeTask(int index) throws ChecklstException {
        index--;

        if (index < 0 || index >= this.taskList.size()) {
            throw new ChecklstException("The task index you have indicated does not exist!");
        }

        Task newTask = taskList.get(index).complete();
        this.taskList.set(index, newTask);

        return newTask;
    }

    public Task deleteTask(int index) throws ChecklstException {
        index--; 

        if (index < 0 || index >= this.taskList.size()) {
            throw new ChecklstException("The task index you have indicated does not exist!");
        }

        Task newTask = taskList.get(index);
        this.taskList.remove(index);

        return newTask;
    }

    /**
     * Returns a TaskList with the filtered tasks.
     * @param input String to filter by.
     * @return Filtered TaskList
     * @throws ChecklstException Exception when no items are found.
     */
    public TaskList findTask(String input) throws ChecklstException {
        List<Task> filteredList = this.taskList.stream()
            .filter(x -> x.name.contains(input))
            .collect(Collectors.toList());
        
        if (filteredList.size() == 0) {
            throw new ChecklstException("No results found!!");
        }

        return new TaskList(filteredList);
    }

    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "Task list is currently empty!";
        }

        String taskListOutput = "1. " + taskList.get(0);
        for (int i = 1; i < taskList.size(); i++) {
            taskListOutput += "\n\t" + String.valueOf(i + 1) + ". " + taskList.get(i);
        }
        return taskListOutput;
    }

    protected List<Task> getTaskList() {
        return this.taskList;
    }

}
