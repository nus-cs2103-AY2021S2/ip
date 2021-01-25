package duke;

import duke.Tasks.Task;

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
}
