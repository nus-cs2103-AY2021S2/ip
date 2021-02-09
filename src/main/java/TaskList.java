import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;
    public static int taskListSize;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task addTodoTask(Todo task) {
        taskList.add(task);
        taskListSize = taskList.size();
        return task;
    }

    public Task addDeadlineTask(Deadline task) {
        taskList.add(task);
        taskListSize = taskList.size();
        return task;
    }

    public Task addEventTask(Event task) {
        taskList.add(task);
        taskListSize = taskList.size();
        return task;
    }

    public Task deleteTask(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        taskListSize = taskList.size();
        return task;
    }

    public Task taskDone(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }

    public int getListSize() {
        return taskList.size();
    }

    public ArrayList<Task> getList() {
        return taskList;
    }
}
