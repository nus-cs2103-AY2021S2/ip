import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final TaskList storage;
    private final File data;
    private final Ui ui;

    public Storage(TaskList tasks, Ui ui) {
        this.storage = tasks;
        this.ui = ui;
        data = new File("data/savedList.txt");
        if (!data.exists()) {
            try {
                data.getParentFile().mkdirs();
                data.createNewFile();
                ui.print("It appears you are using Duke for the first time. Welcome!");
            } catch (IOException e) {
                ui.ioException();
            }
        }
    }

    public void importData() throws FileNotFoundException {
        Scanner sc = new Scanner(data);
        if (!sc.hasNext()) {
            ui.print("Looks like you have no tasks! :)");
            return;
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            int type = Integer.parseInt(String.valueOf(line.charAt(0)));
            char done = line.charAt(2);
            String task = line.substring(3);
            Task newTask = new Task(task, type);
            if (done == '1') {
                newTask.markDone();
            }
            storage.addImport(newTask);
        }
        ui.print("Tasks saved from last session imported! :)");
    }

    public void exportData() throws IOException {
        FileWriter fw = new FileWriter("data/savedList.txt");
        for (int i = 0; i < storage.size(); i++) {
            fw.write(storage.get(i).export());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
