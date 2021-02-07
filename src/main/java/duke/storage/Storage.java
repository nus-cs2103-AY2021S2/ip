package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the external storage used by Duke to remember tasks on restarts. Duke is currently saving tasks to a
 * single text file and reading from that file on startup.
 *
 */
public class Storage {
    private final static String DIR_NAME = System.getProperty("user.dir") + File.separator + "data";
    private final static String FILE_NAME = DIR_NAME + File.separator + "tasks.txt";

    private final File file;

    public Storage() throws IOException {
        File dir = new File(DIR_NAME);
        file = new File(FILE_NAME);

        //noinspection ResultOfMethodCallIgnored
        dir.mkdir();
        //noinspection ResultOfMethodCallIgnored
        file.createNewFile();
    }

    /**
     * Fills the given TaskList with data read from storage. The TaskList will be modified in-place.
     *
     * @param taskLst the TaskList to have data filled in
     * @return taskLst the original TaskList
     * @throws IOException if there is a problem reading from file
     */
    public TaskList fillTaskLst(TaskList taskLst) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                Task t = parseLine(line);
                taskLst.add(t);
            }

            br.close();

        } catch (FileNotFoundException e) {
            assert false : "Data file does not exist even though it is already created in the constructor.";
        }

        return taskLst;
    }

    private Task parseLine(String line) {
        // Need double backslashes to escape the literal "\|" or else it will be parsed as an escape sequence
        String[] words = line.split(" \\| ");
        String taskTypeStr = words[0];

        Task t;

        switch (taskTypeStr) {
        case "T":
            t = Todo.deserialize(line);
            break;
        case "D":
            t = Deadline.deserialize(line);
            break;
        case "E":
            t = Event.deserialize(line);
            break;
        default:
            throw new DukeException("Found invalid task type. Allowed task types: [T, D, E]");
        }

        return t;
    }

    /**
     * Saves the tasks in the given TaskList to storage.
     *
     * @param taskLst the TaskList to retrieve tasks from
     */
    public void saveTaskLst(TaskList taskLst) {
        StringBuilder sb = new StringBuilder();

        taskLst.forEach(t -> {
            sb.append(t.getSerialized());
            sb.append("\n");
        });

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        } catch (Exception e) {
            throw new DukeException(String.format("Error with saveTaskLst: %s", e));
        }
    }
}
