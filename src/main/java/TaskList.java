import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasksList;

    public TaskList(){
        this.tasksList = new ArrayList<>();
    }

    /**
     * Adds a new task to the Task List.
     *
     * @param  newTask  New Task.
     */
    public void addTask(Task newTask){
        tasksList.add(newTask);
    }

    /**
     * Gets size of Task List.
     *
     * @return      updated Task List.
     */
    public int getSize() {
        return this.tasksList.size();
    }

    /**
     * Gets the task at respective index.
     *
     * @param  index task index.
     * @return       task at that index.
     */
    public Task getTask(int index){
        return tasksList.get(index);
    }

    /**
     * Removes task at respective index.
     *
     * @param  index task index.
     */
    public void removeTask(int index){
        tasksList.remove(index - 1);
    }

    /**
     * Displays all tasks.
     *
     */
    public void displayTasks(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasksList.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + tasksList.get(i));
        }
    }
}
