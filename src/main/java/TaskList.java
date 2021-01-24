import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

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
                System.out.println("The file exists. Reading file...");
                readFile();
            }
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
        return fileObject;
    }

    public void readFile() {
        try {
            File fileObject = new File("../../../data/tasks.txt");
            Scanner reader = new Scanner(fileObject);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                processInputData(data);
            }
            System.out.println("Reading done.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void processInputData(String data) {
        char taskType = data.charAt(1);
        String task = new String();
        String time = new String();
        int secondSeg = data.indexOf("(");
        int endSeg = data.indexOf(")");
        task = data.substring(7);
        if(secondSeg != -1 && endSeg != -1) {
            task = data.substring(7, secondSeg);
            time = data.substring(secondSeg + 5, endSeg);
        }
        if (taskType == 'T') {
            tasksList.add(new ToDo(task));
        } else if (taskType == 'D') {
            tasksList.add(new Deadline(task, time));
        } else {
            tasksList.add(new Event(task, time));
        }
        if(data.charAt(4) == 'X') {
            tasksList.get(tasksList.size() - 1).setCompleted();
        }
    }

    public void writeFile() {
        try {
            FileWriter fileWriter = new FileWriter("../../../data/tasks.txt");
            for (int i = 0; i < this.tasksList.size(); i++) {
                fileWriter.write(this.tasksList.get(i).toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An IOException has occurred.");
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
