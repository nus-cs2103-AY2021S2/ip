import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

public class TaskSaver {
    private final String filePath;
    private final File file;
    private static final String deliminator = "\\|";

    public TaskSaver(String path) {
        this.filePath = path;
        file = new File(filePath);
    }

    public Vector<Task> restore() throws DukeException {
        Vector<Task> ret = new Vector<>();
        try (Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] data;
                data = line.split(deliminator, 0);
                switch (data[0]) {
                    case "T":
                        ret.add(new ToDo(data[2]));
                        break;
                    case "D":
                        ret.add(new Deadline(data[2], data[3]));
                        break;
                    case "E":
                        ret.add(new EventTask(data[2], data[3]));
                        break;
                    default:
                        throw new DukeException("Unknown task type");
                }
                if (Boolean.parseBoolean(data[1]))
                    ret.lastElement().markAsDone();
            }
        } catch (FileNotFoundException e) {
            // do nothing
        }
        return ret;
    }

    public void save(final Vector<Task> tasks) throws DukeException {
        try (FileWriter fw = new FileWriter(filePath, false)) {
            writeToFile(fw, tasks);
        } catch (IOException e) {
            throw new DukeException("TaskSaver.save: IOException encountered");
        }
    }

    private void writeToFile(FileWriter fw, Vector<Task> tasks) throws IOException {
        for (Task task : tasks) {
            LinkedList<String> list = task.export();
            fw.write(String.join("|", list) + System.lineSeparator());
        }
    }
}
