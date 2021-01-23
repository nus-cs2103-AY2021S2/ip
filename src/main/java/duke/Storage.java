package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeIOException;
import duke.task.Task;

public class Storage {
    private String filePath;

    public Storage(String fp) {
        this.filePath = fp;
    }

    public ArrayList<String> load() throws DukeIOException {
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }

            ArrayList<String> output = new ArrayList<>();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {
                output.add(line);
                line = br.readLine();
            }
            fr.close();
            return output;
        } catch (IOException e) {
            throw new DukeIOException();
        }
    }

    public void save(TaskManager tm) throws DukeIOException {
        try {
            ArrayList<Task> tasks = tm.getTasks();
            FileWriter fw = new FileWriter(this.filePath);

            for (Task task : tasks) {
                fw.write(task.toFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeIOException();
        }
    }


}
