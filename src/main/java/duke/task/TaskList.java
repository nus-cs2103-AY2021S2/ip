package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(TaskList taskList) {
        this.taskList = new ArrayList<>(taskList.taskList);
    }

    /**
     * Returns task as index i
     *
     * @param i index of task to get
     * @return Task
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Adds a new task to taskList
     *
     * @param task
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task at index idx from taskList
     *
     * @param idx index of task to remove
     * @return Task
     */
    public Task remove(int idx) {
        return taskList.remove(idx);
    }

    /**
     * Marks task at index idx as done
     *
     * @param idx index of task to mark done
     * @throws DukeException
     */
    public void markDone(int idx) throws DukeException {
        try {
            taskList.get(idx).setDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bound.");
        }
    }

    /**
     * Returns the size of current taskList
     *
     * @return int
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the string representation of taskList to be saved in data file
     *
     * @return String
     */
    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task.toSavedString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a deep clone of the taskList.
     *
     * @return TaskList
     */
    public TaskList clone() {
        TaskList newTaskList = new TaskList();
        for (Task t : taskList) {
            newTaskList.add(t.clone());
        }
        return newTaskList;
    }

    public void setTaskList(TaskList other) {
        taskList = other.taskList;
    }
}
