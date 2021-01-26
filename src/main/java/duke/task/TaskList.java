package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task delete(int index) {
        return taskList.remove(index);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void setDone(int index) {
        taskList.get(index).setDone();
    }

    public int size() {
        return taskList.size();
    }

    public TaskList clone() {
        return new TaskList(taskList);
    }

    public TaskList filterByDate(String date) {
        List<Task> printTaskList = new ArrayList<>(taskList);
        LocalDate queryDate = LocalDate.parse(date);
        printTaskList.removeIf(t -> {
            if (t instanceof Deadline) {
                return !(((Deadline) t).getBy().isEqual(queryDate));
            } else if (t instanceof Event) {
                return !((Event) t).getAt().isEqual(queryDate);
            }
            return true;
        });
        return new TaskList(printTaskList);
    }

    public String toStorageString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : taskList) {
            sb.append(String.format("%s%n", t.toStorageString()));
        }
        return sb.toString();
    }
}
