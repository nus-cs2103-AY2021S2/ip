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

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public int getListSize() {
        return taskList.size();
    }

    public ArrayList<Task> getList() {
        return taskList;
    }
}
