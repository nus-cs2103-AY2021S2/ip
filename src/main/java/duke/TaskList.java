package duke;

import java.util.ArrayList;

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
    TaskList() {
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
        this.listOfTasks.remove(taskIndex - 1);
    }

    /**
     * Prints out each task in a new line
     */
    public void print() {
        for (int i = 0; i < listOfTasks.size(); i++)
            System.out.println("    " + this.listOfTasks.get(i).getStatus());
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
    public void printContains(String words) {
        int j = 0;
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (this.listOfTasks.get(i).doesDescriptionContain(words)) {
                j++;
                System.out.println("    " + j + "." + this.listOfTasks.get(i).getStatus());
            }
        }
    }
}
