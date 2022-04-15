package duchess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duchess.Tasks.Deadline;
import duchess.Tasks.Event;
import duchess.Tasks.Task;
import duchess.Tasks.Todo;


public class Storage {

    /** Filewriter instance to write to file */
    private FileWriter writer;

    /** File instance to store data */
    private final File dataFile;

    /** Tasklist instance to store and handle tasks */
    private TaskList tasks;

    /** Constructs a new Storage objective
     *
     * @param path of the file
     * @param tasklist tasks in tasklist
     */
    public Storage(String path, TaskList tasklist) {
        dataFile = new File(path);
        tasks = tasklist;
        try {
            if (!dataFile.exists()) {
                File parent = dataFile.getParentFile();
                parent.mkdir();
                dataFile.createNewFile();
            } else {
                fileToList();
            }
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        }
    }

    /**
     * Reads a file and stores tasks in TaskList
     *
     * */
    public void fileToList() {
        try {
            Scanner reader = new Scanner(dataFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] arr = data.split(" : ", 4);
                String cat = arr[0];
                String completed = arr[1];
                String des = arr[2];
                if (cat.equals("E")) {
                    String date = arr[3];
                    Event event = new Event(des, date);
                    if (completed.equals("1")) {
                        event.checkTask();
                    }
                    tasks.storeTask(event);
                } else if (cat.equals("D")) {
                    String date = arr[3];
                    Deadline deadline = new Deadline(des, date);
                    if (completed.equals("1")) {
                        deadline.checkTask();
                    }
                    tasks.storeTask(deadline);
                } else {
                    Todo todo = new Todo(des);
                    if (completed.equals("1")) {
                        todo.checkTask();
                    }
                    tasks.storeTask(todo);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Goes through TaskList and parses task into string
     *
     * @return String of tasks
     * */
    public String taskListToString() {
        String res = "";
        ArrayList<Task> taskList = tasks.getTaskList();
        for (Task task : taskList) {
            char cat = task.getCategory();
            String name = task.getName();
            int checked;
            if (task.getCompleted()) {
                checked = 1;
            } else {
                checked = 0;
            }
            if (cat == 'E') {
                Event event = (Event) task;
                String date = event.getTime();
                res += cat + " : " + checked + " : " + name + " : " + date + "\n";
            } else if (cat == 'D') {
                Deadline deadline = (Deadline) task;
                String date = deadline.getDeadline();
                res += cat + " : " + checked + " : " + name + " : " + date + "\n";
            } else {
                res += cat + " : " + checked + " : " + name + "\n";
            }
        }
        return res;
    }

    /** Writes the tasks in TaskList to file
     *
     * @throws IOException if there is an IO exception
     * */
    public void write() {
        try {
            writer = new FileWriter(dataFile);
            writer.write(taskListToString());
            writer.close();
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        }
    }
}
