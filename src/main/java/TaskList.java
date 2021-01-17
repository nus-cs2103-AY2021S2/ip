import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasksList;

    public TaskList(){
        this.tasksList = new ArrayList<>();
    }

    public void addTask(Task newTask){
        tasksList.add(newTask);
    }

    public int getSize() {
        return this.tasksList.size();
    }

    public Task getTask(int index){
        return tasksList.get(index);
    }

    public void removeTask(int index){
        tasksList.remove(index - 1);
    }

    public void displayTasks(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasksList.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + tasksList.get(i));
        }
    }
}
