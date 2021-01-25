package duke;

import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.ToDo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            Task task;
            String data = sc.nextLine();
            String[] strings = data.split("// ");
            char taskType = strings[0].charAt(0);
            boolean isCompleted = Integer.parseInt(strings[1].substring(0, 1)) == 1;
            String taskName = strings[2].strip();
            if (taskType == 'T') {
                task = new ToDo(isCompleted, taskName);
            } else if (taskType == 'D') {
                task = new Deadline(isCompleted, taskName, LocalDate.parse(strings[3].strip()));
            } else {
                task = new Event(isCompleted, taskName, LocalDate.parse(strings[3].strip()));
            }
            tasks.add(task);
        }
        return tasks;
    }

    public void overwrite(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (Task task : tasks) {
            fw.write(task.generateFileFormatString() + "\n");
        }
        fw.close();
    }

    public void append(Task task) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(task.generateFileFormatString() + "\n");
        fw.close();
    }
}
