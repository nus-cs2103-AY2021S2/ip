package task;

import java.util.ArrayList;

import exception.ChecklstException;

public class TaskList {
    
    private final ArrayList<Task> taskList = new ArrayList<>();

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

}
