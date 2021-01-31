package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Class handling List of Tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;
    private boolean isDone;

    /**
     * Constructor method for TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
        isDone = false;
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
        list.add(content);
    }

    /**
     * Adds task with the relevant information in the input.
     * To be deprecated when Parser can handle file input.
     *
     * @param task relevant string info for the new Task.
     */
    public void addTask(String[] task) {
        String type = task[0].strip();
        String done = task[1].strip();
        String desc = task[2].strip();
        Task newTask;
        newTask = new ToDo(desc);
        if (type.equals("T")) {
            newTask = new ToDo(desc);
            this.addTask(task);
        } else if (type.equals("D")) {
            String byDate = task[3];
            newTask = new Deadline(desc, byDate);
            this.addTask(task);
        } else if (type.equals("E")) {
            String atDate = task[3];
            newTask = new Event(desc, atDate);
            this.addTask(task);
        } else {
            System.out.println("File has invalid entries");
        }
        if (done.equals("1")) {
            newTask.setDone();
        }
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
        List<Task> tasksWithString = new ArrayList<>();
        for (Task t: list) {
            String taskDesc = t.getDesc();
            if (taskDesc.contains(keyword)) {
                tasksWithString.add(t);
            }
        }
        return tasksWithString;
    }

    /**
     * To be deprecated, i.e move to another class of more relevance.
     */
    public void setExited() {
        this.isDone = true;
    }

    /**
     * To be deprecated, i.e move to another class of more relevance.
     * @return boolean whether app has exited.
     */
    public boolean hasExited() {
        return this.isDone;
    }

}
