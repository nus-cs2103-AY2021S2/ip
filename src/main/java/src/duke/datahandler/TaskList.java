package duke.datahandler;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The TaskList class ia a wrapper class around the ArrayList class with added
 * functionality. The class includes methods that streamlines and refactors
 * arraylist methods in to one method.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Consturcts an empty TaskList
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list
     *
     * @param task task to be added to list
     */
    public void add(Task task) {
        this.listOfTasks.add(task);
    }

    /**
     * Delete a task at the index - 1, due to 0 indexing and user input compensation
     *
     * @param taskIndex index - 1 where the task is to be deleted
     */
    public void delete(int taskIndex) {
        this.listOfTasks.remove(taskIndex);
    }

    /**
     * Prints out each task in a new line
     */
    public String listOutTasks() {
        String output = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            output += "    " + this.listOfTasks.get(i).getStatus() + "\n";
        }
        return output;
    }

    /**
     * return size of TaskList
     *
     * @return size of TaskList
     */
    public int numberOfTasks() {
        return this.listOfTasks.size();
    }

    public Task get(int taskIndex) {
        return this.listOfTasks.get(taskIndex);
    }

    /**
     * Searches for task that contains the word and prints them out
     *
     * @param words word that the task must contain to be printed
     */
    public String findContains(String words) {
        String output = "";
        int j = 0;
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (this.listOfTasks.get(i).doesDescriptionContain(words)) {
                j++;
                output += "    " + j + "." + this.listOfTasks.get(i).getStatus() + "\n";
            }
        }
        return output;
    }

    /**
     * removes the task at the taskindex and replaces it with the new task
     * @param task      task to be put in the task list
     * @param taskIndex deletes the task at this index
     */
    public void update(Task task, int taskIndex) {
        this.delete(taskIndex);
        this.listOfTasks.add(taskIndex, task);
    }
}
