package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * ArrayList adapted to Duke.
 */
public class TaskList {

    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addJob(Task t) {
        this.list.add(t);
    }

    public Task getJob(int index) {
        return this.list.get(index);
    }

    public int getSize() {
        return this.list.size();
    }

    /**
     * Replaces Task at index with newTask.
     *
     * @param index Target Task.
     * @param newTask New Task to add.
     */
    public void replaceJob(int index, Task newTask) {
        this.list.remove(index);
        this.list.add(index, newTask);
    }

    public void deleteJob(int index) {
        this.list.remove(index);
    }

    /**
     * Formats the list to its output format.
     *
     * @param length For newLiner.
     * @return String in correct format.
     */
    public String formatList(int length) {
        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            resultStr.append(StringParser.newLiner((i + 1) + "."
                    + list.get(i).toString(), length));
        }
        return resultStr.toString();
    }
}
