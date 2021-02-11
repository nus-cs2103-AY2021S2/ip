package duke.tasks;

import duke.exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static Task getTask(int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            return tasks.get(taskNum - 1);
        } else {
            throw new DukeException("☹ OOPS!!! Task " + taskNum + " is not in the task list!");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public static void deleteTask(int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            tasks.remove(taskNum - 1);
        } else {
            throw new DukeException("☹ OOPS!!! Task " + taskNum + " is not in the task list!");
        }
    }

    public static void completeTask(int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task taskToComplete = getTask(taskNum);
            taskToComplete.markAsDone();
        } else {
            throw new DukeException("☹ OOPS!!! Task " + taskNum + " is not in the task list!");
        }
    }

    public static TaskList findTask(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.description.matches("(.*)" + keyword + "(.*)")) {
                filteredTasks.add(task);
            }
        }

        return new TaskList(filteredTasks);
    }
}
