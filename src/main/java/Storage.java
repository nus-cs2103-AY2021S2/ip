import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Storage {
    private final String filePath;
    private final File file;
    private static final String deliminator = "\\|";

    public Storage(String path) {
        this.filePath = path;
        file = new File(filePath);
    }

    public Vector<Task> load() throws DukeException {
        Vector<Task> ret = new Vector<>();
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            return ret;
        }

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] data;
            data = line.split(deliminator, 0);
            switch (data[0]) {
                case "T":
                    ret.add(new ToDo(data[2]));
                    break;
                case "D":
                    ret.add(new Deadline(data[2], LocalDateTime.parse(data[3])));
                    break;
                case "E":
                    ret.add(new EventTask(data[2], LocalDateTime.parse(data[3])));
                    break;
                default:
                    throw new DukeException("Unknown task type");
            }

            // mark as read accordingly
            if (Boolean.parseBoolean(data[1]))
                ret.lastElement().markAsDone();
        }
        return ret;
    }

    public void save(final List<Task> tasks) throws DukeException {
        try (FileWriter fw = new FileWriter(filePath, false)) {
            writeToFile(fw, tasks);
        } catch (IOException e) {
            throw new DukeException("TaskSaver.save: IOException encountered");
        }
    }

    private void writeToFile(FileWriter fw, List<Task> tasks) throws IOException {
        for (Task task : tasks) {
            LinkedList<String> list = task.export();
            fw.write(String.join("|", list) + System.lineSeparator());
        }
    }
}
