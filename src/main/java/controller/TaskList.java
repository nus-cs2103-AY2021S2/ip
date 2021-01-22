package controller;

import task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> listOfTasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void addTask(Task t) {
        this.listOfTasks.add(t);
    }

    public Task deleteTask(int number) {
        return this.listOfTasks.remove(number - 1);
    }

    public void markAsDone(int number) {
        this.listOfTasks.get(number - 1).setDone(true);
    }

    public Task getTaskAtIndex(int number) {
        return this.listOfTasks.get(number - 1);
    }

    public int getNumberOfTasks() {
        return this.listOfTasks.size();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            result.append(i + 1).append(". ").append(listOfTasks.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Find the Tasks which names contain the keyword.
     * @param keyword Keyword to be searched against.
     * @return New TaskList containing filtered results.
     */
    public TaskList find(String keyword) {
        return new TaskList(this.listOfTasks.stream()
                .filter((task) -> task.getTaskName().
                        toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new)));
    }
}
