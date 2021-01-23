package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    TaskList() {
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

    public void replaceJob(int index, Task newTask) {
        this.list.remove(index);
        this.list.add(index, newTask);
    }

    public void deleteJob(int index) {
        this.list.remove(index);
    }
}
