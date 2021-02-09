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
     * @param index task number
     */
    public String setTaskDone(int index) {
        String result = new String();
        int actualIndex = index - 1;
        try {
            if (actualIndex < this.tasksList.size()) {
                this.getTask(actualIndex).setCompleted();
                result = result + "Nice! I've marked this task as done: \n" + this.getTask(index - 1);
            } else {
                throw new DukeException("Index is out of range. Please input a correct index.");
            }
        } catch (DukeException de) {
            result = result + de.getMessage();
        }
        return result;
    }

    /**
     * Adds a new task to the Task List.
     *
     * @param newTask new task
     */
    public void addTask(Task newTask){
        tasksList.add(newTask);
    }

    /**
     * Gets size of Task List.
     *
     * @return updated Task List
     */
    public int getSize() {
        return this.tasksList.size();
    }

    /**
     * Gets the task at respective index.
     *
     * @param index task index
     * @return task at that index
     */
    public Task getTask(int index){
        return tasksList.get(index);
    }

    public String searchRelatedText(String cmd) {
        assert tasksList != null : "TaskList is null.";
        ArrayList<Task> temporaryList = new ArrayList<>();
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).taskDesc.contains(cmd)) {
                temporaryList.add(tasksList.get(i));
            }
        }
        if (temporaryList.size() == 0) {
            return "There are no matching keywords to your search.";
        }
        String result = "Here are the matching tasks in your list: \n";
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
            if (actualIndex < this.tasksList.size()) {
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
     * Displays all tasks.
     */
    public String displayTasks() {
        String result = new String();
        result = result + "Here are the tasks in your list: \n";
        for (int i = 0; i < tasksList.size(); i++) {
            result = result + String.valueOf(i + 1) + ". " + tasksList.get(i) + "\n";
	    }
        return result;
    }
}