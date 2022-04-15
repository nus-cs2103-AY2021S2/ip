import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private File file;
    private final String dirPath = System.getProperty("user.dir") + "/data";
    private final String filePath = System.getProperty("user.dir") + "/data/duke.txt";

    public Storage() {
        this.file = new File(System.getProperty("user.dir") + "/data/duke.txt");
    }

    private void print(String toString) {
    }

    public boolean isSavedHistory() {
        return this.file.exists();
    }

    /**
     * Takes in an empty TaskList and adds the stored tasks into it.
     *
     * @param tasklist
     * @throws FileNotFoundException
     */
    public void initialise(TaskList tasklist) throws FileNotFoundException {
        if (isSavedHistory()) {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String current = s.nextLine().toLowerCase();
                assert current != null : "current at initialise in Storage";
                if (current.contains("todo")) {
                    Task task = Todo.readTaskFromStorage(current);
                    tasklist.add(task);
                } else if (current.contains("deadline")) {
                    tasklist.add(Deadline.readTaskFromStorage(current));
                } else if (current.contains("event")) {
                    tasklist.add(Event.readTaskFromStorage(current));
                } else {
                    if (s.hasNext()) {
                        current = s.nextLine();
                    } else {
                        throw new FileNotFoundException("History saved corrupted");
                    }
                }
            }
            s.close();
        } else {
            this.file.mkdirs();
        }
    }

    /**
     * Takes a TaskList that has been added with various Task to store those tasks
     *
     * @param tasklist
     * @throws IOException
     */
    public void saveHistory(TaskList tasklist) throws IOException {
        File file = new File(filePath);
        File dir = new File(dirPath);
        handleNonExistentFiles(file, dir);
        FileWriter fw = new FileWriter(file, false);
        tasklist.forEach(x -> {
            try {
                fw.write(x.saveInStorageAs() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fw.close();
    }

    private void handleNonExistentFiles(File file, File dir) throws IOException {
        if (!Files.isDirectory(Paths.get(dirPath))) {
            // Create data folder and duke.txt if do not exist
            dir.mkdir();
            assert Files.isDirectory(Paths.get(dirPath)) : "Directory does not exist";
            file.createNewFile();
        } else if (!file.exists()) {
            // Create duke.txt if do not exist
            file.createNewFile();
        }
    }
}
