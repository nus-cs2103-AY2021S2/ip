package simulator;

import exception.DukeException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

import java.util.Scanner;

import ui.Ui;

/**
 * Class <code>Storage</code> deals with loading tasks from file and saving task in the file.
 * Contains two method <code>load</code> and <code>save</code>.
 */
public class Storage {
    private final File data;

    /**
     * Constructs a storage with file to be loaded or saved initialized.
     */
    public Storage() {
        data = new File("./data.txt");
    }


    /**
     * Creates a new task with specified <code>type</code>, <code>status</code> and <code>description</code>.
     * @param type type of task.
     * @param status status of task.
     * @param description description of task.
     * @return a Task object.
     */
    public Task newTask(String type, String status, String description) {
        Task task = new Task();
        try {
            if (type.equals("T")) {
                task = new Todo(status, description);
            } else {
                String[] details = description.split("@");
                if (type.equals("D")) {
                    task = new Deadline(status, details);
                } else {
                    task = new Event(status, details);
                }
            }
        } catch (DukeException ex) {
            Ui.printBox(ex.getMessage());
        }
            return task;
    }

    /**
     * Loads from the file into the the specified <code>list</code> and returns a <code>TaskList</code>.
     *
     * @param list tasks loaded.
     * @return loaded list.
     */
    public TaskList load(TaskList list) {
        try {
            if (!data.createNewFile()) {
                Scanner sc = new Scanner(data);
                while (sc.hasNext()) {
                    String[] content = sc.nextLine().split("\\|");
                    String type = content[0];
                    String status = content[1];
                    Task task = newTask(type, status, content[2]);
                    list.addTask(task);
                }
            }
        } catch (IOException ex) {
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
    public String save(TaskList list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(data));
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String status = task.getStatus();
            String type = task.getType();
            String description = task.getDetails();
            assert type.equals("T") || type.equals("D") || type.equals("E") : "Error! Not event,deadline or todo task";
            // check whether the saved data is a todo
            if (type.equals("T")) {
                writer.write(type + "|" + status + "|" + description);
            } else {
                // Check whether the saved data is a deadline or event
                String date = type.equals("D") ? ((Deadline) task).getDate() : ((Event) task).getDate();
                String time;
                if (type.equals("D")) {
                    time = ((Deadline) task).getTime();
                } else {
                    time = ((Event) task).getTime();
                }
                if (time == null ) {
                    writer.write(type + "|" + status + "|" + description + "@" + date);
                } else {
                    writer.write(type + "|" + status + "|" + description + "@" + date + "@" + time);
                }
            }
            writer.newLine();
        }
        writer.close();
        return Ui.SAVE_EXIT_MSG;
    }
}
