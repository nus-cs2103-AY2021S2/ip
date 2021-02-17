package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class in an abstraction of a file loading and a saving system.
 */
public class Storage {
    public static final String DEFAULT_STORAGE_FILE = "data/duke.txt";
    private File f;
    private String filePath;

    /**
     * Constructs a Storage with a specified path.
     * @param filePath file path of where task data is stored
     * @throws DukeException throws exception if the filePath is invalid
     */
    public Storage(String filePath) throws DukeException {
        if (!isValidFileExtension(filePath)) {
            throw new DukeException("\t\tFile should end with .txt extension");
        }
        this.filePath = filePath;
        this.f = new File(filePath);
    }

    /**
     * Constructs an instance of the Storage class.
     */
    public Storage() {
        this.filePath = "data";
        this.f = new File(filePath, "duke.txt");
        this.f.getParentFile().mkdirs();
    }

    /**
     * Checks the validity of a filepath
     * @param filePath the path of the file that is to be checked for validity
     * @return boolean value stating is the file is a valid one
     */
    public boolean isValidFileExtension(String filePath) {
        return filePath.endsWith(".txt");
    }

    /**
     * Loads duke.txt file from the local disk.
     * @return a list of tasks that is written in the duke.txt file
     * @throws DukeException an exception when loading a file from the local disk
     */
    public ArrayList<Task> loadFile() throws DukeException {

        ArrayList<Task> taskArr = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.f);
            while (sc.hasNext()) {
                taskArr.add(stringToTask(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        }
        return taskArr;

    }

    /**
     * Saves a file to the disk with task details.
     * @param TaskArr a list of tasks that needs to be saved to the disk
     * @throws DukeException an exception when saving the file to the local disk
     */
    public void saveFile(ArrayList<Task> TaskArr) throws DukeException {
        try {
            FileWriter fw = new FileWriter(DEFAULT_STORAGE_FILE);
            for (Task t : TaskArr) {
                fw.write(taskToString(t) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error saving the Tasks into a file");
        }
    }

    /**
     * Converts strings to Task instances
     * @param task the task string that need to be converted to a Task Object
     * @return Returns the task object
     * @throws DukeException throws exception
     */
    public Task stringToTask(String task) throws DukeException {
        String[] arr = task.split(", ");
        Task result;
        if (arr.length == 0) {
            throw new DukeException("Read invalid task from the file");
        }
        String taskType = arr[0];

        if (taskType.equals("T")) {
            if (arr.length != 3) {
                throw new DukeException("Read invalid Todo task from the file");
            }
            result = new ToDo(arr[2]);
            if (Integer.valueOf(arr[1]) == 1) {
                result.isDone = true;
            }
            return result;
        } else if (taskType.equals("D")) {
            if (arr.length != 4) {
                throw new DukeException("Read invalid Deadline task from the file");
            }
            result = new Deadline(arr[2], arr[3]);
            if (Integer.valueOf(arr[1]) == 1) {
                result.isDone = true;
            }
        } else if (taskType.equals("E")) {
            if (arr.length != 4) {
                throw new DukeException("Read invalid Event task from the file");
            }
            result = new Event(arr[2], arr[3]);
            if (Integer.valueOf(arr[1]) == 1) {
                result.isDone = true;
            }
        } else {
            throw new DukeException("Read invalid Task type");
        }

        return result;
    }

    /**
     * Converts Tasks to string
     * @param task the task object that need to be converted to a String
     * @return throws exception
     */
    public String taskToString(Task task) {
        int num = 0;
        String result = "";
        if (task.isDone) {
            num = 1;
        }

        if (task instanceof Event) {
            result = "E, " + num
                    + ", " + task.taskDescription
                    + ", " + ((Event) task).at;
        } else if (task instanceof Deadline) {
            result = "D, " + num
                    + ", " + task.taskDescription
                    + ", " + ((Deadline) task).by;
        } else if (task instanceof ToDo) {
            result = "T, " + num
                    + ", " + task.taskDescription;
        }
        return result;

    }
}
