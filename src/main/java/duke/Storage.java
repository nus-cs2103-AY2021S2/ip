package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handle file I/O.
 */
public class Storage {
    private File file;

    /**
     * Construct a Storage from a specified filename.
     * @param name the filename
     */
    Storage(String name) {
        try {
            this.file = new File(name);
            if (file.createNewFile()) {
                System.out.println("Created file: " + file.getName());
            }

        } catch (IOException err) {
            Ui.printException(new DukeException(err.getMessage()));
        }
    }

    /**
     * Read tasks stored in the file.
     * @return the list of tasks read
     */
    public List<Task> read() {
        List<Task> lst = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                char type = str.charAt(1);
                char status = str.charAt(3);
                String desc = str.substring(7);
                if (type == 'T') {
                    lst.add(new ToDo(desc));
                } else if (type == 'D') {
                    int index = desc.indexOf('(');
                    String name = desc.substring(0, index);
                    String time = desc.substring(index + 5, desc.length() - 1);
                    lst.add(new Deadline(name, LocalDate.parse(time, DateTimeFormatter.ofPattern("MMM dd yyyy"))));
                } else if (type == 'E') {
                    int index = desc.indexOf('(');
                    String name = desc.substring(0, index);
                    String time = desc.substring(index + 5, desc.length() - 1);
                    lst.add(new Event(name, LocalDate.parse(time, DateTimeFormatter.ofPattern("MMM dd yyyy"))));
                }
                if (status == 'X') {
                    lst.get(lst.size() - 1).markDone();
                }
            }
        } catch (IOException err) {
            Ui.printException(new DukeException(err.getMessage()));
        }
        return lst;
    }

    /**
     * Write all tasks to given file.
     * @param lst list of tasks to be written
     */
    public void write(List<Task> lst) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            for (Task task: lst) {
                fileWriter.write(task.toString() + '\n');
            }
            fileWriter.close();
        } catch (IOException err) {
            Ui.printException(new DukeException(err.getMessage()));
        }
    }
}
