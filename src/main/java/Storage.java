package duke;

import java.io.File;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * The Storage is used to interact with content stored on hard drive.
 */
public class Storage {
    private Path filepath;

    /** Initialises a storage and creates an empty file in specified directory. */
    public Storage() {
        try {
            File dir = new File("tasklist");
            dir.mkdirs();
            File f = new File(dir, "mytasks.txt");
            f.createNewFile();
            this.filepath  = f.toPath();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /** Writes all the tasks to the txt file stored on hard drive. */
    public void writeToDisk(List<Task> store) {
        try {
            FileWriter fw = new FileWriter("tasklist/mytasks.txt");
            for (Task t: store) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /** Read-in content from previously stored file on hard drive. */
    public List<Task> loadContent() {
        List<Task> store = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.filepath);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                boolean done = false;
                Task t;
                if (line.split("]")[1].replace("[", "") != "") {
                    done = true;
                }
                if (line.contains("T")) {
                    t = new Todo(line.split(" ")[1]);
                } else if (line.contains("D")) {
                    String description = line.split(" ")[1];
                    String by = line.split("\\(by: ")[1].split("\\)")[0];
                    t = new Deadline(description, by);
                } else {
                    String description = line.split(" ")[1];
                    String by = line.split("\\(at: ")[1].split("\\)")[0];
                    t = new Event(description, by);
                }
                if (done) {
                    t.markAsDone();
                }
                store.add(t);
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
        return store;
    }
}
