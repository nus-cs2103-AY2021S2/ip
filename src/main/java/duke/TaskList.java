package duke;

import java.util.ArrayList;

import duke.task.DeadlineTask;
import duke.task.Task;

public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructor for task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Obtains a task based on ID specified.
     * @param id ID of task to be obtained.
     * @return Task to be retrieved.
     */
    public Task getTask(int id) {
        int index = id - 1;
        return tasks.get(index);
    }

    /**
     * Obtains the size of the task list.
     * @return Size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list.
     * @param task Task to be added to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on ID specified.
     * @param id ID of task to be removed.
     */
    public void removeTask(int id) {
        tasks.remove(id - 1);
    }

    /**
     * Marks the ID of the task specified as done.
     * @param id ID of the task to be marked done.
     */
    public void markDone(int id) {
        int index = id - 1;
        Task task = tasks.remove(index);
        task.markDone();
        tasks.add(index, task);
    }

    /**
     * Finds a key word within task list and returns a new list containing tasks with keyword.
     * @param key Keyword to be matched with.
     * @return A new list containing tasks with the keyword specified.
     */
    public TaskList findKeyWord(String key) {
        ArrayList<Task> temp = new ArrayList<>();
        tasks.stream().filter(task -> task.toString().contains(key)).forEach(temp::add);
        return new TaskList(temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n");
            sb.append(i + 1).append(". ");
            sb.append(tasks.get(i).toString());
        }
        return sb.toString();
    }

    /**
     * Checks if the task list contains any clashing deadline tasks.
     * @return True if there are clashing deadline tasks.
     */
    public boolean isAnomalyPresent() {
        ArrayList<Task> deadlineTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof DeadlineTask) {
                deadlineTasks.add(task);
            }
        }
        for (int i = 0; i < deadlineTasks.size(); i++) {
            for (int j = 0; j < deadlineTasks.size(); j++) {
                DeadlineTask firstTask = (DeadlineTask) deadlineTasks.get(i);
                DeadlineTask secondTask = (DeadlineTask) deadlineTasks.get(j);
                boolean isNotTheSameTask = i != j;
                boolean isSameSchedule = firstTask.isClashingSchedule(secondTask);
                if (isNotTheSameTask && isSameSchedule) {
                    return true;
                }
            }
        }
        return false;
    }

}
