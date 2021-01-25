import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Storage {
    private final static String DIR_NAME = System.getProperty("user.dir") + File.separator + "data";
    private final static String FILE_NAME = DIR_NAME + File.separator + "tasks.txt";

    private final File file;

    public Storage() {
        try {
            File dir = new File(DIR_NAME);
            file = new File(FILE_NAME);

            //noinspection ResultOfMethodCallIgnored
            dir.mkdir();
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();

        } catch (Exception e) {
            throw new DukeException(String.format("Error with initialising Storage: %s", e));
        }
    }

    public List<Task> fillTaskLst(List<Task> taskLst) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                Task t = parseLine(line);
                taskLst.add(t);
            }

            br.close();

        } catch (Exception e) {
            throw new DukeException(String.format("Error with fillTaskLst: %s", e));
        }

        return taskLst;
    }

    public Task parseLine(String line) {
        // need double backslashes to escape the literal "\|" or else it will be parsed as an escape sequence
        String[] words = line.split(" \\| ");

        String taskTypeStr = words[0];
        boolean isDone = words[1].equals("1");
        String content = words[2];
        String datetime = words.length == 4 ? words[3] : "";

        Task t;

        switch (taskTypeStr) {
        case "T":
            t = new Todo(content);
            break;
        case "D":
            t = new Deadline(content, datetime);
            break;
        case "E":
            t = new Event(content, datetime);
            break;
        default:
            throw new DukeException("Found invalid task type. Allowed task types: [T, D, E]");
        }

        if (isDone) {
            t.markDone();
        }

        return t;
    }
}
