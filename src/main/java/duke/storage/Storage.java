package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class Storage
 * @author Png Zheng Jie, Sebastian
 * Description: Saves and loads the list of tasks to a designated file path
 */
public class Storage {
    private String filePath;

    /**
     * Constructor: creates a new Storage
     * @param filePath path where the list of tasks is saved
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * loadFile: loads the saved list of tasks
     * @return an ArrayList of tasks
     * @throws DukeException
     */
    public ArrayList<Task> loadFile() throws DukeException {
        File tasksListFile = new File(filePath);
        ArrayList<Task> tasksList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(tasksListFile);

            while (sc.hasNextLine()) {
                String savedTask = sc.nextLine();
                String[] savedTaskDetails = savedTask.split(" \\| ");
                String savedTaskType = savedTaskDetails[0];
                String savedTaskDescription = savedTaskDetails[2];
                boolean isTaskDone = (Integer.parseInt(savedTaskDetails[1]) == 1);
                Task taskToLoad;

                switch (savedTaskType) {
                case "T":
                    taskToLoad = new ToDo(savedTaskDescription);
                    break;
                case "D":
                    taskToLoad = new Deadline(savedTaskDescription, savedTaskDetails[3]);
                    break;
                case "E":
                    taskToLoad = new Event(savedTaskDescription, savedTaskDetails[3]);
                    break;
                default:
                    throw new DukeException("There are issues in loading the saved file of the tasks list.");
                }

                if (isTaskDone) {
                    taskToLoad.markAsDone();
                }

                tasksList.add(taskToLoad);
            }

            return tasksList;
        } catch (IOException e){
            throw new DukeException("There is no saved file of the tasks list in this directory.");
        }
    }

    /**
     * saveFile: save the list of tasks into a text file
     * @param tasks the ArrayList of tasks
     * @throws DukeException
     */
    public void saveFile(ArrayList<Task> tasks) throws DukeException {
        try {
            File tasksListFile = new File(filePath);
            FileWriter fw = new FileWriter(tasksListFile);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task task : tasks) {
                bw.write(task.saveFormat());
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Tasks list cannot be saved.");
        }
    }
}
