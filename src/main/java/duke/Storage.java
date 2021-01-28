package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
    }

    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : taskList.getTasks()) {
            fw.write(t.storeString() + "\n");
        }
        fw.close();
    }

    public List<Task> load() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        List<Task> tempTask = new ArrayList<>();
        while (s.hasNextLine()) {
            String[] strArr = s.nextLine().split(" \\| ");
            String type = strArr[0];
            if (type.equals("T")) {
                tempTask.add(new ToDo(strArr));
            } else if (type.equals("D")) {
                tempTask.add(new Deadline(strArr));
            } else if (type.equals("E")) {
                tempTask.add(new Event(strArr));
            } else {
                System.out.println("Error");
            }
        }
        s.close();
        return tempTask;
    }

}
