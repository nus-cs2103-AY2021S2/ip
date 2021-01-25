import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String LIST_FILE_PATH = "storage/";
    private static final String LIST_FILE = LIST_FILE_PATH + "list.txt";

    File file;
    FileWriter fw;


    public Storage() throws DukeException {
        Path path = Paths.get(LIST_FILE_PATH);
        try {
            Files.createDirectories(path);
            this.file = new File(LIST_FILE);
            if (this.file.exists()) {
                this.fw = new FileWriter(LIST_FILE, true);
            } else {
                this.fw = new FileWriter(LIST_FILE);
            }
        } catch (IOException e) {
            throw new DukeException("Failed to access file. " + e.getMessage());
        }
    }

    public ArrayList<Task> readTasksFromFile() throws DukeException {
        File tasks = this.file;
        Scanner s;
        try {
            s = new Scanner(tasks);
            ArrayList<Task> newList = new ArrayList<>();
            while (s.hasNext()) {
                newList.add(Task.stringToTask(s.nextLine()));
            }
            s.close();
            return newList;
        } catch (TaskException | FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void writeTaskToFile(Task task) throws DukeException {
        try {
            this.fw.write(task.toString());
            this.fw.append(System.getProperty("line.separator"));
            this.fw.close();
        } catch (IOException | NullPointerException e) {
            throw new DukeException("Error writing task to storage/list " + e.getMessage());
        }
    }

    public void clearFile() throws DukeException {
        try {
            this.fw = new FileWriter(LIST_FILE);
        } catch (IOException e) {
            throw new DukeException("Failed to clear file of contents. " + e.getMessage());
        }
    }
}
