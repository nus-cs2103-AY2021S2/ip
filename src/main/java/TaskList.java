import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task changeStatus(int index, boolean done) {
        Task updatedTask = this.taskList.get(index);
        updatedTask.status(done);
        return updatedTask;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void addToDo(Todo task) {
        taskList.add(task);
    }

    public void addEvent(Event task) {
        taskList.add(task);
    }

    public void addDeadline(Deadline task) {
        taskList.add(task);
    }

    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder task = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            task.append(index).append(".").append(taskList.get(i)).append("\n");
        }
        return task.toString();
    }
}
