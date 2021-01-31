package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;


public class Storage {

    /** Filewriter instance to write to file */
    private FileWriter writer;

    /** File instance to store data */
    private File dataFile;

    /** Tasklist instance to store and handle tasks */
    private TaskList tasks;

    /** Constructs a new Storage objective
     *
     * @param path of the file
     * @param tasks in tasklist
     */
    public Storage(String path, TaskList tasks) {
        dataFile = new File(path);
        try {
            if (!dataFile.exists()) {
                File parent = dataFile.getParentFile();
                parent.mkdir();
                dataFile.createNewFile();
            } else {
                fileToList(tasks);
            }
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        }
    }

    /**
     * Reads a file and stores tasks in TaskList
     *
     * @param taskList to store tasks
     * */
    public void fileToList(TaskList taskList) {
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
                    if (completed == "1") {
                        event.checkTask();
                    }
                    taskList.storeTask(event);
                } else if (cat.equals("D")) {
                    String date = arr[3];
                    Deadline deadline = new Deadline(des, date);
                    if (completed.equals("1")) {
                        deadline.checkTask();
                    }
                    taskList.storeTask(deadline);
                } else {
                    Todo todo = new Todo(des);
                    if (completed.equals("1")) {
                        todo.checkTask();
                    }
                    taskList.storeTask(todo);
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
     * @param tasks tasklist
     * @return String of tasks
     * */
    public String taskListToString(TaskList tasks) {
        String res = "";
        ArrayList<Task> taskList = tasks.taskList;
        for (Task task : taskList) {
            char cat = task.getCat();
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
     * @param taskList tasks to be written
     * @throws IOException if there is an IO exception
     * */
    public void write(TaskList taskList) {
        try {
            writer = new FileWriter(dataFile);
            writer.write(taskListToString(taskList));
            writer.close();
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        }
    }

}
