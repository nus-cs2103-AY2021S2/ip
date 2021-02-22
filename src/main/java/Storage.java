import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.Label;

/**
 * IO handler for CS2103T iP. Manages import of saved tasks upon program start and export
 * of tasks upon program termination.
 */
public class Storage {
    private final TaskList storage;
    private final File data;
    private final Ui ui;

    /**
     * Creates a Storage object for data manipulation.
     * @param tasks TaskList to be written into when importing saved tasks.
     * @param ui Any messages generated will be directed to the provided Ui object.
     */
    public Storage(TaskList tasks, Ui ui) {
        this.storage = tasks;
        this.ui = ui;
        data = new File("data/savedList.txt");
        if (!data.exists()) {
            try {
                data.getParentFile().mkdirs();
                data.createNewFile();
                //ui.print("It appears you are using Duke for the first time. Welcome!");
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Imports saved tasks into TaskList.
     * @throws FileNotFoundException When file is not found.
     */
    public Label importData() throws FileNotFoundException {
        Scanner sc = new Scanner(data);
        if (!sc.hasNext()) {
            return ui.print("Looks like you have no tasks! :-)");
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            int type = Integer.parseInt(String.valueOf(line.charAt(0)));
            char done = line.charAt(2);
            assert type < 3 && type > -1;
            assert done == '0' || done == '1';
            String task = line.substring(3);
            Task newTask = new Task(task, type);
            if (done == '1') {
                newTask.markDone();
            }
            storage.addImport(newTask);
        }
        sc.close();
        return ui.print("Tasks saved from last session imported! :)");
    }

    /**
     * Exports saved tasks.
     * @throws IOException When there are IO errors.
     */
    public void exportData() throws IOException {
        FileWriter fw = new FileWriter("data/savedList.txt");
        for (int i = 0; i < storage.size(); i++) {
            fw.write(storage.get(i).export());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
