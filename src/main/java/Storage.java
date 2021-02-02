import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Storage used for loading files from and saving files into the user's computer.
 */
public class Storage {
    private static final String currdir = System.getProperty("user.dir");
    private static final Path path = Paths.get(currdir, "data");
    private static final Path file = Paths.get(currdir, "data", "duke.txt");

    /**
     * Loads saved tasks from the user's computer.
     * @return List of saved tasks.
     * @throws IOException if exception occurs when method is running.
     */
    public ArrayList<Task> loadtasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                if (!task.isEmpty()) {
                    if (task.startsWith("[T]")) {
                        boolean done = task.charAt(4) == 'X';
                        tasks.add(new ToDo(task.substring(7), done));
                    } else if (task.startsWith("[E]")) {
                        boolean done = task.charAt(4) == 'X';
                        int index = task.indexOf('(');
                        int endindex = task.indexOf(')');
                        LocalDate date = LocalDate.parse(task.substring(index + 5, endindex), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        tasks.add(new Event(task.substring(7, index - 1), date, done));
                    } else {
                        boolean done = task.charAt(4) == 'X';
                        int index = task.indexOf('(');
                        int endindex = task.indexOf(')');
                        LocalDate date = LocalDate.parse(task.substring(index + 5, endindex), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        tasks.add(new Event(task.substring(7, index - 1), date, done));
                    }
                }
            }
            sc.close();
            return tasks;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    /**
     * Saves tasks into the user's computer.
     * @param tasks List of tasks to be saved.
     */
    public void savetasks(TaskList tasks) {
        try {
            String string = "";
            for (int i = 0; i < tasks.size(); i++) {
                string += tasks.get(i) + "\n";
            }
            Files.writeString(file, string);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
