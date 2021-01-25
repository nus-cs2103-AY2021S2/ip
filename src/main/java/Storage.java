import java.io.*;

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

    public TaskList fillTaskLst(TaskList taskLst) {
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
