package duke;

import java.util.ArrayList;

import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;


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
     * @return Chosen Task
     * */
    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    /** Find tasks that matches the given string
     *
     * @param s string to search for
     * @return message with matching tasks
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

    /** Adds new todo task to taskList and returns todo task
     *
     * @param name name of new todo task
     * @param tasks tasklist of tasks
     * @return new todo task
     * */
    public Task addTodo(String name, TaskList tasks) {
        Task todo = new Todo(name);
        tasks.storeTask(todo);
        return todo;
    }

    /** Adds new event to taskList and returns event
     *
     * @param name name of new event
     * @param date date of event as string
     * @param tasks taskList of tasks
     * @return new event
     */
    public Task addEvent(String name, String date, TaskList tasks) {
        Task event = new Event(name, date);
        tasks.storeTask(event);
        return event;
    }

    /** Adds new deadline to taskList and returns deadline
     *
     * @param name name of new Deadline
     * @param dueDate due date of deadline task
     * @param tasks taskList of tasks
     * @return new deadline
     */
    public Task addDeadline(String name, String dueDate, TaskList tasks) {
        Task deadline = new Deadline(name, dueDate);
        tasks.storeTask(deadline);
        return deadline;
    }

    /** Checks off task of specified index and returns task
     *
     * @param index index of task to be checked off
     * @param tasks taskList of tasks
     * @return task that has been checked off
     */
    public Task completeTask(int index, TaskList tasks) {
        Task t = tasks.getTask(index);
        tasks.markComplete(index);
        return t;
    }
}
