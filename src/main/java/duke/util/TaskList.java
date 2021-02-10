package duke.util;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> listOfTasks;

    public TaskList(List<Task> listOfTasks) {
        this.listOfTasks = listOfTasks.stream().collect(Collectors.toList()); //deep-copy
    }

    /**
     * Return the size of the TaskList.
     */
    public int size() {
        return this.listOfTasks.size();
    }

    /**
     * Add a task into the TaskList
     * @param task the Task we would like to add to our TaskList
     */
    public void add(Task task) {
        listOfTasks.add(task);
    }

    /**
     * get the i-th element of the TaskList
     * @param i the index of the element in our TaskList
     */
    public Task get(int i) {
        return this.listOfTasks.get(i);
    }

    /**
     * set the i-th element of the TaskList with task
     * @param i the index of the element in our TaskList
     *        task Task we would like to set into the i-th position in the TaskList
     */
    public Task set(int i, Task task) {
        return this.listOfTasks.set(i, task);
    }

    /**
     * remove the i-th element of the TaskList
     * @param i the index of the element we want to remove in our TaskList
     */
    public Task remove(int i) {
        return this.listOfTasks.remove(i);
    }

    /**
     * find the element of TaskList containing toSearch String
     * @param toSearch the String we would like to find inside TaskList
     */
    public List<Task> find(String toSearch) {
        List<Task> listFound;
        Predicate<Task> taskPredicate = task -> task.getJob().contains(toSearch);
        listFound = this.listOfTasks.stream().filter(taskPredicate).collect(Collectors.toList());
        return listFound;
    }

    public List<Task> findExact(String toSearch) {
        List<Task> listFound;
        Predicate<Task> taskPredicate = task -> task.getJob().equals(toSearch);
        listFound = this.listOfTasks.stream().filter(taskPredicate).collect(Collectors.toList());
        return listFound;
    }
}
