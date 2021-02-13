package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskManager {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private ArrayList<Task> list = new ArrayList<>(100);

    /**
     * Adds a todo/event/deadline task to the list of tasks.
     *
     * @param type type of task to be added.
     * @param task description of task to be added to the list.
     * @param isCompleted boolean to indicate whether the task has been completed.
     * @return Task task that was added to the list.
     */
    public Task add(String type, String task, boolean isCompleted) {
        assert !task.equals("") : "Missing task description";
        if (type.equals(TODO)) {
            Task newTask = new ToDo(task, isCompleted);
            list.add(newTask);
            return newTask;
        } else {
            String description = task.split("/")[0];
            String deadline = task.split("/", 2)[1].split(" ", 2)[1];
            if (type.equals(DEADLINE)) {
                Task newTask = new Deadline(description, isCompleted, deadline);
                list.add(newTask);
                return newTask;
            } else {
                Task newTask = new Event(description, isCompleted, deadline);
                list.add(newTask);
                return newTask;
            }
        }
    }

    /**
     * Deletes a specific task from the list of tasks.
     *
     * @param taskId id of task to be deleted.
     * @return Task task that was deleted from the list.
     * @throws DukeException if the task number is invalid.
     */
    public Task delete(int taskId) throws DukeException {
        if (taskId <= list.size() && taskId >= 1) {
            assert taskId >= 0 : "Invalid task number";
            Task deletedTask = list.get(taskId - 1);
            list.remove(taskId - 1);
            return deletedTask;
        } else {
            throw new DukeException("Invalid task number. You only have " + list.size() + " tasks in the list.");
        }
    }

    /**
     * Marks a specific task as done.
     *
     * @param taskId id of task to be marked as done.
     * @return Task task that was marked as done.
     * @throws DukeException if the task number is invalid.
     */
    public Task done(int taskId) throws DukeException {
        if (taskId <= list.size() && taskId >= 1) {
            assert taskId >= 0 : "Invalid task number";
            Task completedTask = list.get(taskId - 1);
            completedTask.markComplete();
            return completedTask;
        } else {
            throw new DukeException("Invalid task number. You only have " + list.size() + " tasks in the list.");
        }
    }

    /**
     * Retrieves a list of tasks that are due on a specified date.
     *
     * @param date date to use and search for tasks that are due.
     * @return ArrayList list of tasks that are due on the date.
     * @throws DukeException if the specified date is not in YYYY-MM-DD format.
     */
    public ArrayList<Task> getTasksOn(String date) {
        LocalDate currentDate = LocalDate.parse(date);
        assert currentDate != null : "Invalid date format";
        System.out.println("Here are the tasks due on " + date + ": ");
        ArrayList<Task> dueList = new ArrayList<>();
        for (Task task : list) {
            if (task.getTaskDate().equals(currentDate)) {
                dueList.add(task);
            }
        }
        return dueList;
    }

    /**
     * Uploads a given list of tasks to the task list.
     *
     * @param storedData list of data to be uploaded to the task list.
     */
    public void upload(ArrayList<String> storedData) {
        for (String task : storedData) {
            String[] arr = task.split(" ", 3);
            String type = arr[0];
            boolean isCompleted = arr[1].equals("1");
            add(type, arr[2], isCompleted);
        }
    }

    /**
     * Retrieves the task list to store into storage.
     *
     * @return ArrayList list of tasks to store.
     */
    public ArrayList<String> retrieveTasksforStorage() {
        ArrayList<String> taskList = new ArrayList<>();
        for (Task task : list) {
            taskList.add(task.getFormattedData());
        }
        return taskList;
    }

    public int getNumOfTasks() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Retrieves a list of tasks that have the specified keyword.
     *
     * @param keyword keyword to use and retrieve matching tasks.
     * @return ArrayList list of tasks that have the keyword.
     */
    public ArrayList<Task> retrieveMatchingTasks(String keyword) {
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                taskList.add(task);
            }
        }
        return taskList;
    }
}
