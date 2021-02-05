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
     * Read pre-existing file from previous usage of bot into a list.
     * If no such file can be found, then create a new .txt file.
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
                if (tokens[0].equals("T")) {
                    task = new Todo(tokens[2]);
                    task.isDone = tokens[1].equals("1");
                    list.add(task);
                    line = reader.readLine();
                } else if (tokens[0].equals("D")) {
                    LocalDate date = LocalDate.parse(tokens[3], DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    task = new Deadline(tokens[2], date);
                    task.isDone = tokens[1].equals("1");
                    list.add(task);
                    line = reader.readLine();
                } else if (tokens[0].equals("E")) {
                    task = new Event(tokens[2], tokens[3]);
                    task.isDone = tokens[1].equals("1");
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
     * Update tasks on file with the updated list of tasks
     *
     * @param updatedList the finalised list of tasks after program terminates
     * @throws FileNotFoundException
     */
    public void save(ArrayList<Task> updatedList) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filePath);
        for (Task task : updatedList) {
            writer.println(task.toString());
        } writer.close();
    }
}
