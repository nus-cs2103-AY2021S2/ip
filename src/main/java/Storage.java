import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


public class Storage {
    // members
    private String DirPath;
    private String Username;

    public Storage (String username) {
        this(Paths.get("").toAbsolutePath().toString() + File.separator + "src" 
                + File.separator + "main" + File.separator + "data", username);
    }

    public Storage (String dirPath, String username) {
        this.DirPath = dirPath;
        this.Username = username;
    }

    public TaskList readTasks () {
        TaskList tasks = new TaskList();

        try {
            File dataFile = new File(this.DirPath + File.separator 
                    + this.Username + ".txt");
                
            Scanner scanner = new Scanner(dataFile);

            while (scanner.hasNextLine()) {
                tasks.add(Parser.commandToTask(scanner.nextLine()));
            }

            scanner.close();
        } catch (IOException | Parser.InvalidTaskTypeException | 
                Task.EmptyDescriptionException E) {
          // empty data
        }

        return tasks;
    }

    public void saveTasks (TaskList tasks) throws IOException {

        File dataDirectory = new File(this.DirPath);

        if (! dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        FileWriter writer = new FileWriter(this.DirPath + File.separator 
                + this.Username + ".txt");
  
        for (Task task: tasks) {
            writer.write(Parser.taskToCommand(task) + "\n");
        }

        writer.close();
    }
}
