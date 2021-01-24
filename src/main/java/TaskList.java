import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {

    public ArrayList<Task> tasksList;
    public File fileObject;
    public FileWriter fileWriter;

    public TaskList(String directory){
        this.tasksList = new ArrayList<>();
        this.fileObject = createFile(directory);
    }

    public File createFile(String directory) {
        try {
            File fileObject = new File(directory);
            if (fileObject.createNewFile()) {
                System.out.println("New file created: " 
                    + fileObject.getName());
            } else {
                System.out.println("The file exists.");
            }
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
        return fileObject;
    }

    public void writeFile(String text) {
        try {
            FileWriter fileWriter = new FileWriter(fileObject.getName());
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    public void setTaskDone(int index) {
        this.getTask(index - 1).setCompleted();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this.getTask(index - 1));
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
        Task temp = this.getTask(index - 1);
        tasksList.remove(index - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(temp);
        System.out.println("Now you have "
            + this.getSize()
            + " tasks in the list.");
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
