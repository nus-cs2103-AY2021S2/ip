import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Storage controls saving into and reading from txt files.
 */
public class Storage {

    /** Constant path of folder that the txt file will be in. */
    private static final String DATA_FOLDER_PATH = "data";

    /** Constant path to txt file. */
    private static final String DUKE_FILE_PATH = "data/duke.txt";

    /** List of tasks */
    private TaskList tasks;

    /**
     * Initializes a newly created Storage object with a given list of tasks.
     *
     * @param tasks List of tasks to save.
     */
    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Loads data from txt file.
     * <p> If folder does not exist, the folder will be created. </p>
     * <p> If txt file does not exist, task list remains empty. Else, txt file will be processed. </p>
     *
     * @throws FileNotFoundException Throws error if the file is not found.
     */
    public void loadData() throws FileNotFoundException {
        File dataFolder = new File(Storage.DATA_FOLDER_PATH);
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File dukeFile = new File(Storage.DUKE_FILE_PATH);
        if (dukeFile.exists()) {
            Scanner s = new Scanner(dukeFile); // create a Scanner using the File as the source
            while (s.hasNext()) {
                readTaskList(s.nextLine());
            }
        }
    }

    /**
     * Takes in each line in txt file and adds it into the task list.
     *
     * @param taskData A line in txt file containing information about a task.
     */
    private void readTaskList(String taskData) {
        String[] splits = taskData.split(" \\| ");
        String taskType = splits[0].trim();
        String isDoneString;
        String description;
        String date;
        if (taskType.equals("T")) {
            assert splits.length == 3: "Error in storage file duke.txt format.";
            isDoneString = splits[1].trim();
            description = splits[2].trim();
            assert (isDoneString.equals("1") || isDoneString.equals("0")) : "Error in storage file duke.txt format.";
            assert (!description.equals("")) : "Error in storage file duke.txt format.";
            Todo addedTask = new Todo(description, isDoneString.equals("1"));
            tasks.addTask(addedTask);
        } else if (taskType.equals("D")) {
            assert splits.length == 4: "Error in storage file duke.txt format.";
            isDoneString = splits[1].trim();
            description = splits[2].trim();
            date = splits[3].trim();
            assert (isDoneString.equals("1") || isDoneString.equals("0")) : "Error in storage file duke.txt format.";
            assert (!description.equals("")) : "Error in storage file duke.txt format.";
            assert (!date.equals("")) : "Error in storage file duke.txt format.";
            try {
                Deadline addedTask = new Deadline(description, isDoneString.equals("1"), date);
                tasks.addTask(addedTask);
            } catch (DukeException e) {
                assert false: "Error in storage file duke.txt format";
            }
        } else if (taskType.equals("E")) {
            assert splits.length == 4: "Error in storage file duke.txt format.";
            isDoneString = splits[1].trim();
            description = splits[2].trim();
            date = splits[3].trim();
            assert (isDoneString.equals("1") || isDoneString.equals("0")) : "Error in storage file duke.txt format.";
            assert (!description.equals("")) : "Error in storage file duke.txt format.";
            assert (!date.equals("")) : "Error in storage file duke.txt format.";
            try {
                Event addedTask = new Event(description, isDoneString.equals("1"), date);
                tasks.addTask(addedTask);
            } catch (DukeException e) {
                assert false: "Error in storage file duke.txt format";
            }
        } else if (!taskType.trim().equals("")) {
            assert false : "Error in storage file duke.txt format.";
        }
    }


    private void writeNewFile(String userInput) throws IOException {
        FileWriter fw = new FileWriter(Storage.DUKE_FILE_PATH);
        fw.write(userInput);
        fw.close();
    }

    /**
     * Creates a new txt file and writes in all the tasks in the task list.
     * <p> Prints out "ERROR" if an error occurs in the process. </p>
     */
    public void writeTaskList() {
        String userInput = tasks.joinToTxt();
        try {
            writeNewFile(userInput);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

}
