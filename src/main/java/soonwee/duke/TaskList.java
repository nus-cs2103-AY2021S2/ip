package soonwee.duke;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a TaskList instance. A TaskList instance will contain a list of
 * task and it can display, add or remove tasks from it.
 */
public class TaskList {

    private ArrayList<Task> tasksList;

    /**
     * Instantiates a TaskList instance.
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public ArrayList<Task> getTasksList() {
        return this.tasksList;
    }

    /**
     * Sets a certain task to be done.
     *
     * @param index task number
     */
    public String setTaskDone(int index) {
        String result = new String();
        int actualIndex = index - 1;
        try {
            if (actualIndex < this.tasksList.size() && actualIndex > -1) {
                this.getTask(actualIndex).setCompleted();
                return "Nice! I've marked this task as done: \n" + this.getTask(index - 1);
            } else {
                throw new DukeException("Index is out of range. Please input a correct index.");
            }
        } catch (DukeException de) {
            result = de.getMessage();
        }
        return result;
    }

    /**
     * Adds a new Task to the task List.
     *
     * @param newTask new Task
     */
    public void addTask(Task newTask) {
        tasksList.add(newTask);
    }

    /**
     * Gets size of task list.
     *
     * @return size of task list
     */
    public int getSize() {
        return this.tasksList.size();
    }


    /**
     * Gets task at the certain index.
     *
     * @param index index of task
     * @return task at that index
     */
    public Task getTask(int index) {
        return tasksList.get(index);
    }

    /**
     * Gets input command and search for tasks that contains the matching characters with input.
     *
     * @param cmd input command
     * @return matching tasks to the input
     */
    public String searchRelatedText(String cmd) {
        assert tasksList != null : "TaskList is null.";
        ArrayList<Task> temporaryList = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.getTaskDescription().contains(cmd)) {
                temporaryList.add(task);
            }
        }
        if (temporaryList.size() == 0) {
            return "There are no matching keywords to your search.";
        }
        String result = "Here are the matching tasks in your list: \n";
        if (temporaryList.size() == 0) {
            result = "There is no matching tasks found.";
            return result;
        }
        for (int j = 0; j < temporaryList.size(); j++) {
            result = result + String.valueOf(j + 1) + ". " + temporaryList.get(j).toString() + "\n";
        }
        return result;
    }

    /**
     * Prints current total tasks.
     *
     * @return a display indicating the number of tasks
     */
    public String printTotalTasks() {
        String result = "Now you have " + this.getSize() + " tasks in the list.";
        return result;
    }

    /**
     * Removes task at respective index.
     *
     * @param index task index
     */
    public String removeTask(int index) {
        int actualIndex = index - 1;
        String result = new String();
        try {
            if (actualIndex < this.tasksList.size() && actualIndex > -1) {
                Task temp = this.getTask(actualIndex);
                this.tasksList.remove(index - 1);
                result = result + "Noted. I've removed this task: \n" + temp + "\n"
                        + "Now you have " + this.getSize() + " tasks in the list.";
            } else {
                throw new DukeException("Index is out of range. Please input a correct index.");
            }
        } catch (DukeException de) {
            result = result + de.getMessage();
        }
        return result;
    }

    /**
     * Displays tasks from the task List.
     *
     * @return output from tasklist.
     */

    public String displayTasks() {
        String result = new String();
        if (tasksList.size() == 0) {
            result = "There is no tasks found. Please add a task!";
            return result;
        }
        result = result + "Here are the tasks in your list: \n";
        for (int i = 0; i < tasksList.size(); i++) {
            result = result + String.valueOf(i + 1) + ". " + tasksList.get(i) + "\n";
        }
        return result;
    }

    /**
     * Sorts the task list by its datetime. Task with earliest time will be displayed first, tasks with no time
     * will be displayed after all tasks with time.
     *
     * @return sorted output of tasks
     */
    public String sortTaskListByDateTime() {
        ArrayList<Task> temporaryTimeList = new ArrayList<>(); //To Store items with dates
        ArrayList<Task> toDoList = new ArrayList<>(); //To store temporary ToDoList
        for (Task task : tasksList) {
            if (task.getDateTime() != null) {
                temporaryTimeList.add(task);
            } else {
                toDoList.add(task);
            }
        }
        Collections.sort(temporaryTimeList, new DateTimeComparator());
        for (Task task : toDoList) {
            temporaryTimeList.add(task);
        }
        tasksList = temporaryTimeList;
        return displayTasks();
    }
}
