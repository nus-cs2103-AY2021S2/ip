import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage to save session.
 * <p> Default path is "data/dukeData.txt" if unstated.
 */
public class Storage {
    private File file;
    private static final String DEFAULT_PATH = "data/dukeData.txt";

    public Storage() {
        file = new File(DEFAULT_PATH);
    }

    public Storage(String path) {
        file = new File(path);
    }

    public void saveTaskList(List<Task> taskList) throws DukeException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }

        FileWriter fw;
        try {
            fw = new FileWriter(file);

            for (Task t : taskList) {
                fw.write(String.join(";", t.exportData()) + "\n");
            }

            fw.close();

        } catch (IOException e) {
            throw new DukeException("Please delete \"data/dukeData.txt\" file and run this command again!");
        }
    }


    /**
     * Reads and returns checklist
     * @return List of tasks
     */
    public List<Task> loadTaskList() throws DukeException {
        List<Task> lst = new ArrayList<>();
        Scanner sc;

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("No save found!");
        }

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            try {
                Parser.checkImportFormat(input);
            } catch (DukeInputException e) {
                throw new DukeException("File incorrect format. Unable to load");
            }

            String[] s = input.split(";");
            
            switch (s[0]) {
            case "T":
                lst.add(Todo.importData(s));
                break;
            case "D":
                lst.add(Deadline.importData(s));
                break;
            case "E":
                lst.add(Event.importData(s));
                break;
            }
        }
        return lst;
    }
}
