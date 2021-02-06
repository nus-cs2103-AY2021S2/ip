import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {

    /**
     * Default path to store the user tasks.
     */
    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";
    public TaskList tasks;

    public Storage() {
        tasks = new TaskList();
    }

    /**
     * Read file if it exist, else will create a new file with the name.
     *
     * @throws IOException Throw IO exception.
     */
    public void readOrCreateFile() throws IOException {
        File myObj = new File(DEFAULT_STORAGE_FILEPATH);
        if (myObj.exists()) {
            readFileIntoList(DEFAULT_STORAGE_FILEPATH, tasks);
        } else {
            //noinspection ResultOfMethodCallIgnored
            myObj.createNewFile();
        }
    }

    /**
     * Read the existing task file and create the list of tasks when the program is run.
     *
     * @param file  The name of the file.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     */
    public void readFileIntoList(String file, TaskList tasks) {
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String object : lines) {
            if (object.charAt(1) == 'T') {
                if (object.charAt(4) == '-') {
                    tasks.add(new Todo(object.substring(7), true));
                } else {
                    tasks.add(new Todo(object.substring(7), false));
                }
            } else {
                String dateInfo = object.substring(object.indexOf("(") + 5, object.indexOf(")"));
                if (object.charAt(1) == 'D') {
                    if (object.charAt(4) == '-') {
                        tasks.add(new Deadline(object.substring(7, object.indexOf("(") - 1), dateInfo, true, true));
                    } else {
                        tasks.add(new Deadline(object.substring(7, object.indexOf("(") - 1), dateInfo, false, true));
                    }
                } else if (object.charAt(1) == 'E') {
                    if (object.charAt(4) == '-') {
                        tasks.add(new Event(object.substring(7, object.indexOf("(") - 1), dateInfo, true, true));
                    } else {
                        tasks.add(new Event(object.substring(7, object.indexOf("(") - 1), dateInfo, false, true));
                    }
                }
            }
            Parser.taskAdded();
        }
    }

    /**
     * Update the tasks into the file at the end of the program.
     *
     * @throws FileNotFoundException Throw exception if file does not exist, should not happen.
     */
    public void writeListIntoFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(DEFAULT_STORAGE_FILEPATH);
        ArrayList<Task> items = tasks.getTaskList();
        for (Task item : items) {
            writer.println(item.toString());
        }
        writer.close();
    }
}
