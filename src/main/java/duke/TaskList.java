package duke;

import java.util.ArrayList;

import duke.Tasks.Task;


public class TaskList {
    /** Array list of tasks */
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /** Returns tasklist */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /** Retrieves task from TaskList and marks
     * it as completed
     *
     * @param index position of task in TaskList
     * */
    public void markComplete(int index) {
        Task temp = this.taskList.get(index - 1);
        temp.checkTask();
    }

    /** Removes task from TaskList
     *
     * @param task index of task in TaskList
     * */
    public void deleteTask(int task) {
        this.taskList.remove(task - 1);
    }

    /** Adds new task to TaskList
     *
     * @param task to be added
     * */
    public void storeTask(Task task) {
        this.taskList.add(task);
    }

    /** Retrieves and returns task from TaskList
     *
     * @param index of task in TaskList
     * @return Chosen Task*/
    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    /** Find tasks that matches the given string
     *
     * @param s string to search for
     */
    public String findTasks(String s) {
        ArrayList<Task> matched = new ArrayList<>();
        String msg = "";
        for (Task t : taskList) {
            String name = t.getName();
            if (name.contains(s)) {
                matched.add(t);
            }
        }
        if (matched.size() >= 1) {
            msg += "Duchess: Here are the matching tasks in your list:";
            for (int i = 0; i < matched.size(); i++) {
                msg += "\n" + (i + 1) + ". " + matched.get(i);
            }
        } else {
            msg += "Duchess: No matching tasks found!";
        }
        return msg;
    }
}
