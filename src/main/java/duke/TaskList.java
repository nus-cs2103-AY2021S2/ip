package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> list;
    private boolean isDone;

    public TaskList() {
        list = new ArrayList<>();
        isDone = false;
    }

    public ArrayList<Task> getTasks() {
        return this.list;
    }

    public void addTask(Task content) {
        list.add(content);
    }

    public void addTask(String[] tasks) {
        String type = tasks[0].strip();
        String done = tasks[1].strip();
        String desc = tasks[2].strip();
        Task task;
        task = new ToDo(desc);
        if (type.equals("T")) {
            task = new ToDo(desc);
            this.addTask(task);
        } else if (type.equals("D")) {
            String byDate = tasks[3];
            task = new Deadline(desc,byDate);
            this.addTask(task);
        } else if (type.equals("E")) {
            String atDate = tasks[3];
            task = new Event(desc,atDate);
            this.addTask(task);
        } else {
            System.out.println("File has invalid entries");
        }
        if (done.equals("1")) {
            task.setDone();
        }
    }

    public void setTaskDone(int count) throws IllegalArgumentException {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Error: duke.task.Task number out of range.");
        } else {
            list.get(count-1).setDone();
        }
    }

    public Task deleteTask(int count) throws IllegalArgumentException {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Error: duke.task.Task number out of range.");
        } else {
            Task removedTask = list.remove(count-1);
            return removedTask;
        }
    }

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

    public void setExited() {
        this.isDone = true;
    }

    public boolean hasExited() {
        return this.isDone;
    }

}
