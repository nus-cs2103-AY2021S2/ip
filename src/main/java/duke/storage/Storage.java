package duke.storage;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.DueDate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage loads existing tasks from the file and saves tasks to the file when required
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(this.filePath);
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            String currentTask = fileScanner.nextLine();
            String[] items = currentTask.split(" \\| ");
            boolean done = (items[1].equals("1") ? true : false);
            Task temp = new Todo(items[2]);
            if (items[0].equals("E")) {
                LocalDate date = LocalDate.parse(items[3], formatter);
                temp = new Event(items[2], date);
            } else if (items[0].equals("D")) {
                LocalDate date = LocalDate.parse(items[3], formatter);
                temp = new Deadline(items[2], date);
            }
            if (done) {
                temp = temp.markAsDone();
            }
            tasks.add(temp);
        }
        return tasks;
    }

    public void save(TaskList tasks) {
        ArrayList<Task> store = tasks.getList();
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : store) {
                String rmb = "";
                if (task instanceof Event) {
                    rmb += "E";
                } else if (task instanceof Deadline) {
                    rmb += "D";
                } else if (task instanceof Todo) {
                    rmb += "T";
                }
                rmb += " | ";
                if (task.isCompleted()) {
                    rmb += "1";
                } else {
                    rmb += "0";
                }
                rmb += " | ";
                rmb += task.getDescription();
                if (task instanceof DueDate) {
                    rmb += " | ";
                    rmb += ((DueDate) task).getDueDate();
                }
                fw.write(rmb + "\n");
            }
            fw.close();
        } catch (IOException e) {
        }
    }
}
