package duke.component;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    //@@author stein414-reused
    //DukeResponse abstraction adapted from https://github.com/stein414/ip
    private static final String WORKING_PATH = System.getProperty("user.dir");
    private static final String DEFAULT_PATH = java.nio.file.Paths.get(WORKING_PATH, "duke.txt").toString();

    private File dataFile;

    /**
     * Creates a Storage with default path.
     */
    public Storage() {
        this(DEFAULT_PATH);
    }

    /**
     * Creates a Storage with specified path.
     * @param filePath
     */
    public Storage(String filePath) {
        this.dataFile = new File(filePath);
    }

    /**
     * Loads lines from the specified filePath.
     * @return string array of input
     * @throws IOException
     */
    public String[] load() throws IOException {
        dataFile.createNewFile();

        ArrayList<String> lines = new ArrayList<>();
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(dataFile.getAbsolutePath());
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return lines.toArray(new String[lines.size()]);
    }

    /**
     * Writes lines into the specified filePath.
     * @param tl
     * @throws IOException
     */
    public void save(TaskList tl) throws IOException {
        dataFile.createNewFile();

        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(dataFile.getAbsolutePath());
            bw = new BufferedWriter(fw);
            for (Task t : tl.getTasks()) {
                String line = "";
                String isDone = t.getIsDone() ? "1" : "0";
                if (t instanceof ToDo) {
                    line = "T|" + isDone + "|" + t.getName();
                } else if (t instanceof Deadline) {
                    Deadline dl = (Deadline) t;
                    line = "D|" + isDone + "|" + dl.getName() + "|" + dl.getSaveBy();
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    line = "D|" + isDone + "|" + e.getName() + "|" + e.getSaveAt();
                }
                bw.write(line);
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
}
