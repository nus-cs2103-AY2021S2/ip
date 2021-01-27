import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public String getPath(){
        return this.filePath;
    }

    /**
     * Return list of existing tasks stored in local text file.
     *
     * @return List of task details (type, status, description and time).
     * @throws DukeException If local file is not found.
     */
    public ArrayList<String> load() throws DukeException {
        try {
            ArrayList<String> tasks = new ArrayList<>();
            File f = new File(this.filePath);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                tasks.add(scanner.nextLine());
            }
            return tasks;
        } catch (FileNotFoundException e){
            throw new DukeException("OOPS!! File is not found.");
        }
    }

    /**
     * Store the TaskList in local text file.
     *
     * @param taskList TaskList containing Task objects.
     * @throws DukeException If fail to write the file.
     */
    public void updateTaskList(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            ArrayList<Task> tasks = taskList.getTaskList();
            for (Task t : tasks) {
                writer.write(t.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e){
            throw new DukeException("OOPS!! Task list failed to update.");
        }
    }
}
