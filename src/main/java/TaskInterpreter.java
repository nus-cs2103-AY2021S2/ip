import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskInterpreter {
    public static void saveTasks (ArrayList<Task> tasks, String username) throws IOException {

        String dataDirpath = Paths.get("").toAbsolutePath().toString() 
                + File.separator + "src" + File.separator + "main" 
                + File.separator + "data";

        File dataDirectory = new File(dataDirpath);
        if (! dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        String dataFilepath = dataDirpath + File.separator + username + ".txt";

        FileWriter writer = new FileWriter(dataFilepath);
        for (Task task: tasks) {
            writer.write(task.taskParseCommand() + "\n");
        }

        writer.close();
    }

    public static ArrayList<Task> readTasks (String username) {

        String dataFilepath = Paths.get("").toAbsolutePath().toString() 
                + File.separator + "src" + File.separator + "main" 
                + File.separator + "data" + File.separator + username + ".txt";

        ArrayList<Task> Tasks = new ArrayList<Task>();

        try {
            File dataFile = new File(dataFilepath);
            Scanner scanner = new Scanner(dataFile);

            while (scanner.hasNextLine()) {
                String[] parsedCommand = scanner.nextLine().split(" :: ");

                switch (parsedCommand [0]) {
                case "T":
                    Tasks.add(new ToDo(parsedCommand));
                    break;
                case "D":
                    Tasks.add(new Deadline(parsedCommand));
                    break;
                case "E":
                    Tasks.add(new Event(parsedCommand));
                    break;
                }
            }

            scanner.close();
        } catch (IOException E) {
            // pass
        }
        
        return Tasks;
    }
}