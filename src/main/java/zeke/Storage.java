package zeke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to save tasks to and load tasks from file on local machine.
 */
public class Storage {

    protected String filePath;

    /**
     * Constructor for Storage class.
     * Initializes a Storage object that contains filePath.
     *
     * @param filePath file path to file where user wants his task list saved and loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks to file locally.
     *
     * @param list list of Task objects.
     * @throws IOException if file path cannot be found.
     */
    public void saveTasks(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String fileContent = "";
        for (Task task: list) {
            int num;
            String description = task.getDescription();
            char type = task.getType();
            if (!task.getDoneStatus()) {
                num = 0;
            } else {
                num = 1;
            }
            if (type == 'T') {
                fileContent += type + " ; " + num + " ; " + description + "\n";
            } else if (type == 'D') {
                Deadline deadline = (Deadline) task;
                fileContent += type + " ; " + num + " ; " + description + " ; " + deadline.getDate() + "\n";
            } else {
                assert type == 'E' : type + ": Type should be E";
                Event event = (Event) task;
                fileContent += type + " ; " + num + " ; " + description + " ; " + event.getDate() + "\n";
            }
        }
        fw.write(fileContent);
        fw.close();
    }

    /**
     * Loads tasks from file locally.
     *
     * @return an ArrayList of Task objects.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        File savedTasksFile = null;
        try {
            savedTasksFile = new File(this.filePath);
            if (!savedTasksFile.exists()) {
                if (!savedTasksFile.getParentFile().exists()) {
                    File parentDir = savedTasksFile.getParentFile();
                    parentDir.mkdir();
                }
                savedTasksFile.createNewFile();
            }
            Scanner s = new Scanner(savedTasksFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                assert line.contains(";") : "Saved task does not contain ; but should";
                String[] taskArr = line.split(" ; ", 4);
                String type = taskArr[0];
                String num = taskArr[1];
                String description = taskArr[2];
                if (type.equals("T")) {
                    Todo todo = new Todo(description);
                    if (num.equals("1")) {
                        todo.done();
                    }
                    list.add(todo);
                } else if (type.equals("D")) {
                    String when = taskArr[3];
                    Deadline deadline = new Deadline(description, when);
                    if (num.equals("1")) {
                        deadline.done();
                    }
                    list.add(deadline);
                } else {
                    assert type.equals("E") : type + ": Type should be E";
                    String when = taskArr[3];
                    Event event = new Event(description, when);
                    if (num.equals("1")) {
                        event.done();
                    }
                    list.add(event);
                }
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
