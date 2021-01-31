package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getIndex(int i) {
        return taskList.get(i);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void removeIndex(int i) {
        this.taskList.remove(i);
    }

    /**
     * Returns a TaskList of all tasks containing the keyword.
     *
     * @param keyword The keyword the user wants to search for.
     * @return Tasklist of all tasks containing the keyword.
     * @throws DukeException if no task containing the keyword exists.
     */
    public TaskList find(String keyword) throws DukeException {
        TaskList toReturn = new TaskList();
        for (Task t : this.taskList) {
            if (t.getTaskName().contains(keyword)) {
                toReturn.add(t);
            }
        }
        if (toReturn.getSize() == 0) {
            throw new DukeException("Sorry human, no such task seems to exists.");
        }
        return toReturn;
    }
}
