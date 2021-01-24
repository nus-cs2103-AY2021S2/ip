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
    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";

    public void readOrCreateFile(ArrayList<Task> tasks) throws IOException {
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
     * @param file The name of the file.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     */
    public void readFileIntoList(String file, ArrayList<Task> tasks) {
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String object: lines) {
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
            Duke.taskAdded();
        }
    }

    public void writeListIntoFile(ArrayList<Task> tasks) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(DEFAULT_STORAGE_FILEPATH);
        for (Task item: tasks) {
            writer.println(item.toString());
        }
        writer.close();
    }
}
