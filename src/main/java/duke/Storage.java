package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class to read and write data from a .txt file.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
    }

    /**
     * Saves the TaskList into a file.
     * 
     * @param taskList The TaskList to be saved.
     * @throws IOException If file does not exist.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : taskList.getTasks()) {
            fw.write(t.storeString() + "\n");
        }
        fw.close();
    }

    /**
     * Loads Tasks from a file and stores them in a List.
     * 
     * @return A List containing all the tasks.
     * @throws FileNotFoundException If the file does not exist.
     */
    public List<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        List<Task> tempTasks = new ArrayList<>();
        while (s.hasNextLine()) {
            String[] strArr = s.nextLine().split(" \\| ");
            String type = strArr[0];
            switch (type) {
            case "T":
                tempTasks.add(new ToDo(strArr));
                break;
            case "D":
                tempTasks.add(new Deadline(strArr));
                break;
            case "E":
                tempTasks.add(new Event(strArr));
                break;
            default:
                System.out.println("Error");
                break;
            }
        }
        s.close();
        return tempTasks;
    }

}
