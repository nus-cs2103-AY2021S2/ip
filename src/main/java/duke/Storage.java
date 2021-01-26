package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        file = new File(filePath);
        File folder = file.getParentFile();
        if (!folder.exists())
            folder.mkdir();
    }

    public List<Task> load() throws DukeException {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
        List<Task> taskList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String item = sc.nextLine();
            String[] items = item.split(" \\| ");
            char type = items[0].charAt(0);
            int done = Integer.parseInt(items[1]);
            String desc = items[2];
            switch (type) {
            case 'T':
                taskList.add(new ToDo(done, desc));
                break;
            case 'E':
                taskList.add(new Event(done, desc, items[3]));
                break;
            case 'D':
                taskList.add(new Deadline(done, desc, items[3]));
                break;
            }
        }
        sc.close();
        return taskList;
    }

    public void saveFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(taskList.toStorageString());
            fileWriter.close();
        } catch (IOException ex) {
            throw new DukeException("Error Saving file");
        }
    }
}
