package duke;

import duke.Tasks.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    /** Array list of tasks */
    public ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /** Retrieves task from TaskList and marks
     * it as completed
     *
     * @param Position of task in TaskList
     * */
    public void markComplete(int n) {
        Task temp = this.taskList.get(n - 1);
        temp.checkTask();
    }

    /** Removes task from TaskList
     *
     * @param Position of task in TaskList
     * */
    public void deleteTask(int task) {
        this.taskList.remove(task - 1);
    }

    /** Adds new task to TaskList
     *
     * @param new Task
     * */
    public void storeTask(Task task) {
        this.taskList.add(task);
    }

    /** Retrieves and returns task from TaskList
     *
     * @param Position of task in TaskList
     * @return Chosen Task*/
    public Task getTask(int n) {
        return this.taskList.get(n - 1);
    }

    public void findTasks(String s) {
        ArrayList<Task> matched = new ArrayList<>();
        for(Task t : taskList) {
            String name = t.getName();
            if(name.contains(s)) {
                matched.add(t);
            }
        }
        if(matched.size() >= 1) {
            String msg = "Duchess: Here are the matching tasks in your list:";
            for(int i = 0; i < matched.size(); i++) {
                msg+= "\n" +  (i + 1) + ". " + matched.get(i);
            }
            System.out.println(msg);
        } else {
            System.out.println("Duchess: No matching tasks found!");
        }
    }
}
