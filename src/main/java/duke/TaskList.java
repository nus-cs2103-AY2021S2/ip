package duke;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.task.*;

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
            this.addTask(newTask);
        } else if (type.equals("D")) {
            String byDate = task[3];
            newTask = new Deadline(desc, byDate);
            this.addTask(newTask);
        } else if (type.equals("E")) {
            String atDate = task[3];
            newTask = new Event(desc, atDate);
        } else {
            throw new IllegalArgumentException("Invalid type. Duke.txt may have been tampered.");
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
     * @throws IllegalArgumentException count is out of range
     */
    public void setTaskDone(int count) throws IllegalArgumentException {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Error: Task number out of range.");
        } else {
            list.get(count - 1).setDone();
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
