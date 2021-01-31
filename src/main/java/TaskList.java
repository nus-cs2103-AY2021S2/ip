import java.util.ArrayList;

public class TaskList {
    
    private final ArrayList<Task> taskList = new ArrayList<>();

    public String add(Task task) {
        this.taskList.add(task);
        return String.format("Added: %s\n\tYou now have %d task(s) in the list!", task, this.taskList.size());
    }
 
    public Task get(int index) {
        return this.taskList.get(index);
    }

    public Task completeTask(int index) {
        index--;

        Task newTask = taskList.get(index).complete();
        this.taskList.set(index, newTask);

        return newTask;
    }

    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "Task list is currently empty!";
        }

        String taskListOutput = "1. " + taskList.get(0);
        for (int i = 1; i < taskList.size(); i++) {
            taskListOutput += "\n\t" + String.valueOf(i + 1) + ". " + taskList.get(i);
        }
        return taskListOutput;
    }

}
