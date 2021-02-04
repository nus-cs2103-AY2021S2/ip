import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks from/to stored text file.
 */
public class Storage {
    private static final String path = "data/duke.txt";

    public Storage() {
        try {
            new File("data").mkdir();
            File file = new File(path);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads list of tasks from file
     *
     * @return List of tasks
     */
    public List<Task> loadTasks() {
        List<Task> tl = new ArrayList<>();
        List<String> lines = getLines();
        for (String line : lines) {
            try {
                Task task = parseLine(line);
                tl.add(task);
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }
        return tl;
    }

    /**
     * Save list of tasks to file
     *
     * @param list List of tasks.
     */
    public void saveTasks(List<Task> list) {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            lines.add(list.get(i).toFileString());
        }
        writeLines(lines);
    }

    private List<String> getLines() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Task parseLine(String line) throws DukeException {
        String[] parts = line.split("\\|");
        String type = parts[0];
        boolean isDone = Boolean.valueOf(parts[1]);
        String desc = parts[2];
        switch (type) {
        case "T":
            return new Todo(desc, isDone);
        case "D":
            return new Deadline(desc, isDone, parts[3]);
        case "E":
            return new Event(desc, isDone, parts[3]);
        default:
            throw new DukeException("String parsing failed");
        }
    }

    private void writeLines(List<String> lines) {
        try {
            FileWriter fw = new FileWriter(path);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lines.size(); i++) {
                sb.append(lines.get(i));
                if (i != lines.size() - 1) {
                    sb.append("\n");
                }
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}