package soonwee.duke;

import java.util.ArrayList;

/**
 * Represents a TaskList instance. A TaskList instance will contain a list of
 * task and it can display, add or remove tasks from it.
 */
public class TaskList {

    public ArrayList<Task> tasksList;

    /**
     * Instantiate TaskList instance.
     */
    public TaskList(){
        this.tasksList = new ArrayList<>();
    }

    /**
     * Set a certain task to be done.
     *
     * @param index task number.
     */
    public void setTaskDone(int index) {
        this.getTask(index - 1).setCompleted();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this.getTask(index - 1));
    }

    /**
     * Adds a new task to the Task List.
     *
     * @param newTask new task.
     */
    public void addTask(Task newTask){
        tasksList.add(newTask);
    }

    /**
     * Gets size of Task List.
     *
     * @return updated Task List.
     */
    public int getSize() {
        return this.tasksList.size();
    }

    /**
     * Gets the task at respective index.
     *
     * @param index task index.
     * @return task at that index.
     */
    public Task getTask(int index){
        return tasksList.get(index);
    }

    public void searchText(String cmd) {
        ArrayList<Task> temporaryList = new ArrayList<>();
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).taskDesc.contains(cmd)) {
                temporaryList.add(tasksList.get(i));
            }
        }
        displayTasks(temporaryList);
    }

    /**
     * Removes task at respective index.
     *
     * @param index task index.
     */
    public void removeTask(int index) {
        Task temp = this.getTask(index - 1);
        tasksList.remove(index - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(temp);
        System.out.println("Now you have " + this.getSize() + " tasks in the list.");
    }

    /**
     * Displays all tasks.
     */
    public void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + tasksList.get(i));
	}
    }

    /**
     * Displays all tasks in the TaskList.
     *
     * @param taskList temporary task list.
     */
    public void displayTasks(ArrayList<Task> taskList) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }
    }
}