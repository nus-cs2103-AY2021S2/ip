package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handle file I/O.
 */
public class Storage {
    private final Path storagePath;
    private List<String> taskLst;

    /**
     * Construct a Storage from a specified filepath.
     * @param storagePath the specified path
     */
    public Storage(Path storagePath) {
        this.storagePath = storagePath;
        try {
            Path parent = storagePath.getParent();
            Files.createDirectories(parent);

            if (!Files.exists(storagePath)) {
                Files.createFile(storagePath);
            }

            assert Files.exists(storagePath);
            taskLst = Files.readAllLines(storagePath);

        } catch (IOException err) {
            System.out.println("File read error: " + err.getMessage());
        }
    }

    /**
     * Read tasks stored in the file.
     * @return the list of tasks read
     */
    public ArrayList<Task> read() {
        ArrayList<Task> lst = new ArrayList<>();
        for (String line: this.taskLst) {
            char type = line.charAt(1);
            String desc = line.substring(7);
            int index;
            String name;
            String time;
            char done = line.charAt(3);
            switch (type) {
            case 'T':
                lst.add(new ToDo(desc));
                break;
            case 'D':
                index = desc.indexOf('(');
                name = desc.substring(0, index);
                time = desc.substring(index + 5, desc.length() - 1);
                lst.add(new Deadline(name, LocalDate.parse(time, DateTimeFormatter.ofPattern("MMM dd yyyy"))));
                break;
            case 'E':
                index = desc.indexOf('(');
                name = desc.substring(0, index);
                time = desc.substring(index + 5, desc.length() - 1);
                lst.add(new Event(name, LocalDate.parse(time, DateTimeFormatter.ofPattern("MMM dd yyyy"))));
                break;
            default:
                System.out.println("Task undetermined");
                break;
            }
            if (done == 'X') {
                lst.get(lst.size() - 1).markDone();
            }
        }
        return lst;
    }

    /**
     * Write all tasks to given file.
     * @param taskList list of tasks to be written
     */
    public void write(TaskList taskList) {
        try {
            File storageFile = new File(String.valueOf(storagePath));
            FileWriter fileWriter = new FileWriter(storageFile);
            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.get(i).toString() + '\n');
            }
            fileWriter.close();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
