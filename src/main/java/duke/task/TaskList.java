package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void setDone(int index) {
        tasks.get(index).setDone();
    }

    public int size() {
        return tasks.size();
    }

    public TaskList clone() {
        return new TaskList(tasks);
    }

    public void clear() {
        tasks.clear();
    }

    public void setAllDone() {
        for (Task task : tasks) {
            task.setDone();
        }
    }

    public boolean isAllDone() {
        for (Task task : tasks) {
            if(!task.getDone()) {
                return false;
            }
        }
        return true;
    }

    public TaskList filterByDate(String date) {
        List<Task> printTasks = new ArrayList<>(tasks);
        LocalDate queryDate = LocalDate.parse(date);
        printTasks.removeIf(t -> {
            if (t instanceof Deadline) {
                return !(((Deadline) t).getBy().isEqual(queryDate));
            } else if (t instanceof Event) {
                return !((Event) t).getAt().isEqual(queryDate);
            }
            return true;
        });
        return new TaskList(printTasks);
    }

    public String toStorageString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(String.format("%s%n", t.toStorageString()));
        }
        return sb.toString();
    }
}
