package soonwee.duke;

import java.util.ArrayList;

/**
 * Represents a TaskList instance. A TaskList instance will contain a list of
 * task and it can display, add or remove tasks from it.
 */
public class TaskList {

    public ArrayList<Task> tasksList;

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
                return "Nice! I've marked this task as done: \n" + this.getTask(index - 1);
            } else {
                throw new DukeException("Index is out of range. Please input a correct index.");
            }
        } catch (DukeException de) {
            result = de.getMessage();
        }
        return result;
    }

    public void addTask(Task newTask){
        tasksList.add(newTask);
    }

    public int getSize() {
        return this.tasksList.size();
    }


    public Task getTask(int index){
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
            if (task.taskDesc.contains(cmd)) {
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

    public String displayTasks() {
        String result = new String();
        result = result + "Here are the tasks in your list: \n";
        for (int i = 0; i < tasksList.size(); i++) {
            result = result + String.valueOf(i + 1) + ". " + tasksList.get(i) + "\n";
	    }
        return result;
    }
}