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
        if (splits[0].equals("T")) {
            Todo addedTask = new Todo(Arrays.asList(splits).get(2), Arrays.asList(splits).get(1).equals("1"));
            tasks.addTask(addedTask);
        } else if (splits[0].equals("D")) {
            try {
                Deadline addedTask = new Deadline(Arrays.asList(splits).get(2),
                        Arrays.asList(splits).get(1).equals("1"), Arrays.asList(splits).get(3));
                tasks.addTask(addedTask);
            } catch (DukeException e) {
                System.out.println(e);
            }
        } else if (splits[0].equals("E")) {
            try {
                Event addedTask = new Event(Arrays.asList(splits).get(2),
                        Arrays.asList(splits).get(1).equals("1"), Arrays.asList(splits).get(3));
                tasks.addTask(addedTask);
            } catch (DukeException e) {
                System.out.println(e);
            }
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
