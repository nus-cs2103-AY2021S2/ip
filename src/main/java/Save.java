import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {
    protected static String currentDirectory = System.getProperty("user.dir");
    protected static Path path = java.nio.file.Paths.get(currentDirectory, "data.txt");
    protected static boolean pathExists = java.nio.file.Files.exists(path);

    public static void saveData() throws IOException {
        try {
            if (pathExists) {
                java.nio.file.Files.write(path, "".getBytes(),
                        StandardOpenOption.TRUNCATE_EXISTING); // Clear previous contents
            }

            // Transfer contents from tasks ArrayList to data.txt
            for (Task t : Response.tasks) {
                byte[] bytes = t.saveTask().getBytes();
                if (!pathExists) {
                    java.nio.file.Files.write(path, bytes);
                    pathExists = true;
                } else {
                    java.nio.file.Files.write(path, bytes, StandardOpenOption.APPEND);
                }
            }
        } catch (IOException e) {
            System.out.println("error: cannot save data!");
        }
    }

    public static ArrayList<Task> loadData() throws FileNotFoundException {
        if (pathExists) {
            Scanner taskList = new Scanner(new File(String.valueOf(path)));
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (taskList.hasNextLine()) {
                String currentTask = taskList.nextLine();
                char typeOfTask = currentTask.charAt(0);
                boolean isTaskDone = (currentTask.charAt(2) == '1');
                int temp = currentTask.indexOf('|');
                Task taskToAdd = new Task("");

                if (typeOfTask == 'T') {
                    taskToAdd = new Todo(currentTask.substring(4));
                } else if (typeOfTask == 'D') {
                    taskToAdd = new Deadline(currentTask.substring(4, temp - 1),
                            currentTask.substring(temp + 1));
                } else if (typeOfTask == 'E') {
                    taskToAdd = new Event(currentTask.substring(4, temp - 1),
                            currentTask.substring(temp + 1));
                }
                if (isTaskDone) {
                    taskToAdd.markAsDone();
                }
                tasks.add(taskToAdd);
            }
            return tasks;
        } else {
            return new ArrayList<Task>();
        }
    }
}
