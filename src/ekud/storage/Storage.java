package ekud.storage;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.regex.*;

import ekud.common.exception.*;
import ekud.task.*;

public class Storage {
    private static final String deliminator = "\\|";
    private static final Pattern PARENT_DIRECTORY = Pattern.compile("(?<parentDirectory>^.*/).*$");
    private final String filePath;
    private final File file;
    private boolean canSave;

    public Storage(String path) {
        filePath = path;
        Matcher matcher = PARENT_DIRECTORY.matcher(filePath);
        if (matcher.matches()) {
            String parentDirectory = matcher.group("parentDirectory") == null ? "./" : matcher.group("parentDirectory");
            File folder = new File(parentDirectory);
            if (!folder.exists()) {
                canSave = folder.mkdirs();
            } else {
                canSave = true;
            }
        }

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
            if (Boolean.parseBoolean(data[1])) {
                ret.lastElement().markAsDone();
            }
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
