import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList() {
        taskArrayList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public int getSize() {
        return taskArrayList.size();
    }

    public Task getTask(int index) {
        return taskArrayList.get(index);
    }

    public TaskList addTask(Task task) {
        taskArrayList.add(task);
        return new TaskList(taskArrayList);
    }

    public TaskList deleteTask(int index) {
        taskArrayList.remove(index);
        return new TaskList(taskArrayList);
    }

    public TaskList completeTask(int index) {
        ArrayList<Task> output = taskArrayList;
        output.set(index, taskArrayList.get(index).completeTask());
        return new TaskList(output);
    }

    // returns a list of all tasks
    public String getList() {

        String output = "";

        for (int i = 0; i < taskArrayList.size(); i++) {
            if (i == taskArrayList.size() - 1) {
                output = output + getTask(i).taskStatus();
            } else {
                output = output + getTask(i).taskStatus() + "\n";
            }
        }

        return output;
    }

    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }
}
