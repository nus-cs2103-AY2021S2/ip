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

    public ArrayList<String> load() throws DukeException {
        try {
            ArrayList<String> tasks = new ArrayList<>();
            File f = new File(this.filePath); // create a File for the given file path
            Scanner scanner = new Scanner(f); // create a Scanner using the File as the source
            while (scanner.hasNext()) {
                tasks.add(scanner.nextLine());
            }
            return tasks;
        } catch (FileNotFoundException e){
            throw new DukeException("OOPS!! File is not found.");
        }
    }

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
