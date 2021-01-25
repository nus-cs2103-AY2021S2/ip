import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Paths;

public class TaskInterpreter {
    public static void saveTasks (ArrayList<Task> Tasks, String Username) {        
        String fpath = Paths.get("").toAbsolutePath().toString() +
            File.separator + "src" + File.separator + "main" + 
            File.separator + "data" + File.separator + Username + ".txt";
    
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Task task: Tasks) {
                writer.write(task.creationCommand() + "\n");
            }
            writer.close();
        } catch (IOException E) {
            System.out.println("Unable to save Tasks.");
            E.printStackTrace();
        }
    }

    public static ArrayList<Task> readTasks (String Username) {
        String fpath = Paths.get("").toAbsolutePath().toString() +
            File.separator + "src" + File.separator + "main" + 
            File.separator + "data" + File.separator + Username + ".txt";

        ArrayList<Task> Tasks = new ArrayList<Task>();
        try {
            File f = new File(fpath);
            Scanner scanner = new Scanner(f);

            while (scanner.hasNextLine()) {
                String[] creationCommand = scanner.nextLine().split(" :: ");
              
                switch (creationCommand[0]) {
                    case "T":
                        Tasks.add(new ToDo(creationCommand));
                        break;
                    case "D":
                        Tasks.add(new Deadline(creationCommand));
                        break;
                    case "E":
                        Tasks.add(new Event(creationCommand));
                        break;
                }
            }

            scanner.close();
        } catch (IOException E) {}

        return Tasks;
    }
}