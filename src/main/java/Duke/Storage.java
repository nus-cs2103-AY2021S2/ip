package Duke;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from a file and saving tasks to the file
 */
public class Storage {

    String filePath;
    ArrayList<Task> tasks;
    File myFile;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
        this.myFile = new File(filePath);

        if (!myFile.createNewFile()) {
            scanTaskList();
        }
    }

    /**
     * Scans the file and save the tasks into an ArrayList of Duke.Tasks.Task
     *
     * @throws FileNotFoundException is thrown when there the file could not be found
     */
    public void scanTaskList() throws FileNotFoundException {
        Scanner sc = new Scanner(myFile);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            Task task = getTask(input);
            markTaskAsDone(input, task);
            tasks.add(task);
        }
    }

    /**
     * Marks the task as done if the input contains [O]
     *
     * @param input A string
     * @param task  Task to be marked as done
     */
    private void markTaskAsDone(String input, Task task) {
        if (input.contains("[O]")) {
            task.markAsDone();
        }
    }

    /**
     * Gets the Task as represented from the input
     *
     * @param input A string of task description
     * @return Task that is represented from the string
     */
    private Task getTask(String input) {
        Task task;
        if (input.contains("[T]")) {
            String[] tokens = input.split("] ", 2);
            String taskInfo = tokens[1];
            task = new ToDo(taskInfo);
        } else if (input.contains("[D]")) {
            String[] tokens = input.split("] ", 2);
            String[] nextTokens = tokens[1].split(" ", 2);
            String date = nextTokens[1].substring(nextTokens[1].indexOf(':') + 2, nextTokens[1].indexOf(')'));
            task = new Deadline(nextTokens[0], date);
        } else {
            String[] tokens = input.split("] ", 2);
            String[] nextTokens = tokens[1].split(" ", 2);
            String date = nextTokens[1].substring(nextTokens[1].indexOf(':') + 2, nextTokens[1].indexOf(')'));
            task = new Event(nextTokens[0], date);
        }
        return task;
    }

    /**
     * Stores the <code>TaskList</code> into a file
     *
     * @param tasklist A class that stores the ArrayList of <code>Tasks</code>
     * @throws IOException is thrown when there is an error related to input and output
     */
    public void writeToFile(TaskList tasklist) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        ArrayList<Task> tasks = tasklist.getList();

        for (Task t : tasks) {
            fw.write(t + "\n");
        }

        fw.close();
    }

    /**
     * Loads the ArrayList
     *
     * @return Returns the ArrayList of <code>Duke.Tasks.Task</code>
     */
    public ArrayList<Task> load() {
        return tasks;
    }

}
