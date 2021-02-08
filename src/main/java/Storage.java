import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * This class deals with loading tasks from a file and saving tasks
 * in a file.
 */
public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves existing tasks saved from previous sessions on a file and
     * transfers the tasks into TaskList.
     *
     * @param lst list of tasks.
     * @throws IOException if file path is invalid.
     */
    public void retrieveTasks(TaskList lst) throws IOException {
        File data = new File(filePath);
        if (!data.exists()) {
            data.createNewFile();
        } else {
            Scanner s = new Scanner(data);
            while (s.hasNextLine()) {
                String txt = s.nextLine();
                lst.readTask(txt);
            }
        }
        assert data.exists();
    }

    /**
     * Stores existing tasks into a file to be retrieved in other sessions by
     * retrieving the tasks in TaskList.
     *
     * @param lst list of tasks.
     * @throws IOException if file path is invalid.
     */
    public void storeTasks(TaskList lst) throws IOException {
        File data = new File(filePath);
        assert data.exists();
        // store Tasks in a file
        FileWriter fw = new FileWriter(data, false);
        List<Task> taskList = lst.fetchTasks();

        for (Task item : taskList) {
            fw.write(item.getDescription() + "\n");
        }
        fw.close();
    }

}
