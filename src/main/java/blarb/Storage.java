package blarb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * {@code Storage} reads and writes the storage file for the task list.
 */
class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends a task to the tasklist file.
     *
     * @param task Task to be added.
     * @throws IOException Issues with writing into the file.
     */
    public void file(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.encode());
        fw.write("\n");
        fw.close();
    }

    /**
     * Rewrites the entire tasklist file.
     *
     * @throws IOException Issues with writing into the file.
     */
    public void refile(Tasklist list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        list.forEach(task -> {
            try {
                fw.write(task.encode());
                fw.write("\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        fw.close();
    }

    /**
     * Reads the storage file and loads the tasks into a list.
     *
     * @return A {@code List} of the converted {@code Task}s.
     * @throws IOException Issues with accessing the file.
     */
    public List<Task> load() throws IOException {
        List<Task> list = new ArrayList<>(100);
        File file = new File(filePath);
        File parent = file.getParentFile();
        parent.mkdirs();
        file.createNewFile();
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");
        sc.forEachRemaining(str -> {
            String[] tokens = str.split(" / ");
            Task task;
            switch (tokens[0]) {
            case "T":
                task = new ToDo(tokens[2]);
                break;
            case "D":
                task = new Deadline(tokens[2], tokens[3]);
                break;
            case "E":
                task = new Event(tokens[2], tokens[3]);
                break;
            default:
                assert false;
            }
            if (Integer.parseInt(tokens[1]) == 1) {
                task.markAsDone();
            }
            list.add(task);
        });
        sc.close();
        return list;
    }
}
