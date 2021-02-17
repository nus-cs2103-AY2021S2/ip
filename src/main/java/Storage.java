import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Storage Object.
 * @author Arihant Jain
 */
public class Storage {

    private final String filePath;

    /**
     * Instantiates a new Storage object.
     *
     * @param filePath the file path of text file we are reading and writing to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Create file.
     *
     * @param path the path
     * @throws DukeException.FileLoadError the file load error
     */
    public static void createFile(Path path)
            throws DukeException.FileLoadError {
                File newFile = new File(path.toString());
                try {
                    newFile.createNewFile();
                } catch (IOException e) {
                    throw new DukeException.FileLoadError("File at: " +
                            path.toString() + " cannot be created");
                }

    }

    /**
     * Load array list.
     *
     * @return the array list of tasks loaded from text file
     * @throws DukeException.FileLoadError the file load error
     */
    public ArrayList<Task> load() throws DukeException.FileLoadError {

        ArrayList<Task> loadTasks = new ArrayList<>();
        //check whether file exists
        File file = new File(this.filePath);
        Path path = Paths.get(this.filePath);

        if (!file.isFile()) {
            createFile(path);
            this.load();
        } else {
            try {
                Scanner sc = new Scanner(new File(path.toString()));
                while (sc.hasNextLine()) {
                    String fileLine = sc.nextLine().trim();

                    if (fileLine.charAt(0) == 'T') {
                        Todo newTask = new Todo(fileLine.substring(8));
                        if (fileLine.charAt(4) == '1') {
                            newTask.markAsDone();
                        }
                        loadTasks.add(newTask);
                    } else if (fileLine.charAt(0) == 'D') {
                        //index of last "|"
                        int idx = fileLine.lastIndexOf("|");
                        Deadlines newTask = new Deadlines(fileLine.substring(8, idx - 1),
                                fileLine.substring(idx + 2));
                        if (fileLine.charAt(4) == '1') {
                            newTask.markAsDone();
                        }
                        loadTasks.add(newTask);
                    } else if (fileLine.charAt(0) == 'E') {
                        int idx = fileLine.lastIndexOf("|");
                        Events newTask =
                                new Events(fileLine.substring(8, idx - 1),
                                fileLine.substring(idx + 2));
                        if (fileLine.charAt(4) == '1') {
                            newTask.markAsDone();
                        }
                        loadTasks.add(newTask);
                    }

                }
            } catch (FileNotFoundException e) {
                throw new DukeException.FileLoadError("File at: "
                        + path.toString() + " cannot be read");
            }
        }
        return loadTasks;
    }

    /**
     * Save.
     *
     * @param taskList the tasks that will be saved to the text file
     * @throws DukeException.FileLoadError the file load error
     */
    public void save(TaskList taskList) throws DukeException.FileLoadError {
        Path path = Paths.get(this.filePath);
        String strOut = "";

        // type | 1-> done / 0-> not done | description | date & time
        for (int i = 0; i < taskList.getTasksCount(); i++) {
            //String stat = "";
            String str = "";
            if (taskList.getTasksList().get(i).getStatusIcon().equals(" ")) {
                str = str + "0";
            } else {
                str = str + "1";
            }

            str = "| " + str + " | " +
                    taskList.getTasksList().get(i).description;

            if (taskList.getTasksList().get(i) instanceof Deadlines) {
                str = "D " + str + " | " +
                        ((Deadlines) taskList.getTasksList().get(i)).getBy();
            } else if (taskList.getTasksList().get(i) instanceof Events) {
                str = "E " + str + " | " +
                        ((Events) taskList.getTasksList().get(i)).getAt();
            } else {
                str = "T " + str;
            }

            if (i != taskList.getTasksList().size() - 1) {
                strOut = strOut + str + "\n";
            } else {
                strOut = strOut + str;
            }
        }

        try {
            Files.write(path,
                    strOut.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            try {
                createFile(path);
                this.save(taskList);
            } catch (BaseException err) {
                throw new DukeException.FileLoadError("File at: " + path.toString() + " cannot be written");
            }

        }




    }
}
