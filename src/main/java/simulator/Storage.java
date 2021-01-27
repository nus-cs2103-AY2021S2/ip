package simulator;

import exception.DukeException;
import task.*;
import ui.Ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class <code>Storage</code> deals with loading tasks from file and saving taks in the file.
 * Contains two method <code>load</code> and <code>save</code>.
 */
public class Storage {
    private File data;

    /**
     * Constructs a storage with file to be loaded or saved initialized.
     */
    public Storage() {
        data = new File("./data.txt");
    }

    /**
     * Loads from the file into the the specified <code>list</code> and returns a <code>TaskList</code>.
     *
     * @param list tasks loaded.
     * @return loaded list.
     */
    public TaskList load(TaskList list) {
        try {
            if (data.createNewFile()) {
                Ui.printBox("No Save Record Detected... \n"
                        + "     Creating New List! :)");
            } else {
                Ui.printBox("Saved Record Detected... \n"
                        + "     Retrieving List! :)");
                Scanner sc = new Scanner(data);
                while (sc.hasNext()) {
                    String[] content = sc.nextLine().split("\\|");
                    String command = content[0];
                    String status = content[1];
                    Task task;
                    if (command.equals("T")) {
                        task = new Todo(status, content[2]);
                    } else {
                        String[] description = content[2].split("@");
                        if (command.equals("D")) {
                            task = new Deadline(status, description);
                        } else {
                            task = new Event(status, description);
                        }
                    }
                    list.addTask(task);
                }
            }
        } catch (DukeException | IOException ex) {
            Ui.printBox(ex.getMessage());
        }
        return list;
    }

    /**
     * Save the task in the specified <code>list</code> into the file.
     *
     * @param list tasks saved
     * @throws IOException IOException if file not found
     */
    public void save(TaskList list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(data));
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String status = task.getStatus();
            String type = task.getType();
            String description = task.getDetails();
            if (type.equals("T")) {
                writer.write(type + "|" + status + "|" + description);
            } else {
                String date = type.equals("D") ? ((Deadline) task).getDate() : ((Event) task).getDate();
                if ((type.equals("D")
                        ? ((Deadline) task).getTime() == null
                        : ((Event) task).getTime() == null)) {
                    writer.write(type + "|" + status + "|" + description + "@" + date);
                } else {
                    String time = type.equals("D") ? ((Deadline) task).getTime() : ((Event) task).getTime();
                    writer.write(type + "|" + status + "|" + description + "@" + date + "@" + time);
                }
            }
            writer.newLine();
        }
        Ui.printBox("List Saved Successfully!\n"
                + "     " + Ui.EXIT_MSG);
        writer.close();
    }
}
