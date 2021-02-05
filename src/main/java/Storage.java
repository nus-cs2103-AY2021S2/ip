import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Storage deals with loading tasks from file and saving tasks in file.
 *
 * @author Amanda Ang
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads pre-existing file from previous usage of bot into a list.
     * If no such file can be found, then create a new .txt file to store tasks.
     *
     * @return existing list of tasks
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        BufferedReader reader;
        ArrayList<Task> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(this.filePath));
            String line;
            Task task;
            line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(" \\| ");
                String typeIndicator = tokens[0];
                boolean completionIndicator = tokens[1].equals("1");
                String description = tokens[2];
                if (typeIndicator.equals("T")) {
                    task = new Todo(description);
                    boolean hasDone = completionIndicator;
                    task.setDone(hasDone);
                    list.add(task);
                    line = reader.readLine();
                } else if (typeIndicator.equals("D")) {
                    String by = tokens[3];
                    LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    task = new Deadline(description, date);
                    boolean hasDone = completionIndicator;
                    task.setDone(hasDone);
                    list.add(task);
                    line = reader.readLine();
                } else if (typeIndicator.equals("E")) {
                    String at = tokens[3];
                    task = new Event(description, at);
                    boolean hasDone = completionIndicator;
                    task.setDone(hasDone);
                    list.add(task);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            new File("duke.txt");
        }
        return list;
    }

    /**
     * Updates tasks on file with the updated list of tasks
     *
     * @param updatedList the finalised list of tasks after program terminates
     * @throws FileNotFoundException
     */
    public void save(ArrayList<Task> updatedList) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filePath);
        for (Task task : updatedList) {
            writer.println(task.toString());
        }
        writer.close();
    }
}
