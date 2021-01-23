package ekud.storage;

import ekud.common.exception.DukeException;
import ekud.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    final private String filePath;
    final private File file;
    final private static String deliminator = "\\|";
    final private static Pattern PARENT_DIRECTORY = Pattern.compile("(?<parentDirectory>^.*/).*$");

    private boolean canSave;

    public Storage(String path) {
        this.filePath = path;
        Matcher matcher = PARENT_DIRECTORY.matcher(this.filePath);
        if (matcher.matches()) {
            String parentDirectory = matcher.group("parentDirectory") == null ? "./" : matcher.group("parentDirectory");
            File folder = new File(parentDirectory);
            if (!folder.exists()) {
                this.canSave = folder.mkdirs();
            }
        }

        this.file = new File(filePath);
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

    private void writeToFile(FileWriter fw, List<Task> tasks) throws IOException {
        for (Task task : tasks) {
            LinkedList<String> list = task.export();
            fw.write(String.join("|", list) + System.lineSeparator());
        }
    }

    public void save(final TaskList tasks) throws DukeException {
        if (!canSave) {
            return;
        }

        List<Task> list = tasks.export();
        try (FileWriter fw = new FileWriter(filePath, false)) {
            writeToFile(fw, list);
        } catch (IOException e) {
            throw new DukeException("Storage: IOException encountered");
        }
    }
}
