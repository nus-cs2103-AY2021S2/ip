import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task accessTask(int taskNumber) {
        return taskList.get(taskNumber);
    }

    public void markTaskAsDone(int taskNumber) {
        taskList.get(taskNumber).markAsDone();
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber);
    }

    public int length() {
        return taskList.size();
    }

    public TaskList filterByTask(String type) {
        if (type.equals("ToDo")) {
            return new TaskList(taskList
                    .stream()
                    .filter(p -> p instanceof ToDo)
                    .collect(Collectors.toList()));
        } else if (type.equals("Deadline")) {
            return new TaskList(taskList
                    .stream()
                    .filter(p -> p instanceof Deadline)
                    .collect(Collectors.toList()));
        } else if (type.equals("Event")) {
            return new TaskList(taskList
                    .stream()
                    .filter(p -> p instanceof Event)
                    .collect(Collectors.toList()));
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String rank = String.valueOf(i + 1);
            list.append(rank).append(".").append(taskList.get(i));
            if (i != taskList.size() - 1) {
                list.append("\n");
            }
        }
        return list.toString();
    }
}
