package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Priority;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Class handling List of Tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructor method for TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Gets the ArrayList of Tasks.
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getTasks() {
        return this.list;
    }

    /**
     * Gets the task given the provided index number.
     * @param count the index of the task
     * @return Task at the given index
     */
    public Task getTask(int count) {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Error: Task number out of range.");
        } else {
            return list.get(count - 1);
        }
    }

    /**
     * Adds task into the ArrayList of Tasks
     * @param content task to be added
     */
    public void addTask(Task content) {
        assert content != null : "Task should not be null";
        list.add(content);
    }

    /**
     * Adds task with the relevant information in the input.
     * To be deprecated when Parser can handle file input.
     *
     * @param task relevant string info for the new Task.
     */
    public void addTask(String[] task) throws IllegalArgumentException {
        String type = task[0].strip();
        String done = task[1].strip();
        String priority = task[2].strip();
        String desc = task[3].strip();

        Task newTask;

        if (type.equals("T")) {
            newTask = new ToDo(desc);
        } else if (type.equals("D")) {
            String byDate = task[4];
            newTask = new Deadline(desc, byDate);
        } else if (type.equals("E")) {
            String atDate = task[4];
            newTask = new Event(desc, atDate);
        } else {
            throw new IllegalArgumentException("Error: Invalid type. Duke.txt may have been tampered.");
        }

        if (done.equals("1")) {
            newTask.setDone();
        }
        newTask.setPriority(Priority.valueOf(Integer.parseInt(priority)));

        this.addTask(newTask);
    }

    /**
     * Sets the task at index to be done.
     *
     * @param count the index of Task to be set as Done
     * @throws IllegalArgumentException count is out of range or task is already done.
     */
    public void setTaskDone(int count) throws IllegalArgumentException {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Error: Task number out of range.");
        } else {
            if (!list.get(count - 1).getDone()) {
                list.get(count - 1).setDone();
            } else {
                throw new IllegalArgumentException("Error: Task is already done");
            }
        }
    }

    /**
     * Deletes the task at index.
     * @param count the index of task to be deleted.
     * @return the task that was deleted.
     * @throws IllegalArgumentException count is out of range
     */
    public Task deleteTask(int count) throws IllegalArgumentException {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Error: Task number out of range.");
        } else {
            return list.remove(count - 1);
        }
    }

    /**
     * Finds a list of Tasks with the relevant keyword in their description.
     * @param keyword relevant keyword to be searched.
     * @return List of Tasks with the relevant keyword.
     */
    public List<Task> findTasksWithString(String keyword) {
        Predicate<Task> byKeyword = task-> task.getDesc().contains(keyword);
        return list.stream()
                .filter(byKeyword)
                .collect(Collectors.toList());
    }
}
