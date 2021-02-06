package duke.register;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Storage class to store the data in a file
 */
public class Storage {
    private String path;
    private File file;

    /**
     * Constructor to initialize storage
     */
    public Storage(String filePath) {
        this.path = filePath;
        file = new File(filePath);
    }

    /**
     * Checks if the file exist
     */
    private void createIfNotExist() {
        if (file.exists()) {
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the data from the file and returns then in a task list
     *
     * @return
     * @throws DukeException
     */
    public TaskList load() throws DukeException {
        try {
            TaskList taskList = new TaskList();
            Scanner fio = new Scanner(file);
            while (fio.hasNextLine()) {
                String line = fio.nextLine();
                String[] command = line.split(" ");
                String type = command[0];
                String status = command[command.length - 1];
                if (type.equals("todo")) {
                    TodoTask task = new TodoTask(createTask(command));
                    if (status.equals("done")) {
                        task.markDone();
                    }
                    taskList.addTask(task);
                } else if (type.equals("deadline")) {
                    DeadlineTask task = new DeadlineTask(createTask(command));
                    if (status.equals("done")) {
                        task.markDone();
                    }
                    taskList.addTask(task);
                } else if (type.equals("event")) {
                    EventTask task = new EventTask(createTask(command));
                    if (status.equals("done")) {
                        task.markDone();
                    }
                    taskList.addTask(task);
                } else if (type.equals("add")) {
                    taskList.addTask(new Notes(createTask(command)));
                }
            }
            fio.close();
            return taskList;
        } catch (IOException e) {
            throw new DukeException("failed to load file");
        }
    }

    /**
     * Creates a task line from the command array
     *
     * @param command
     * @return task line
     */
    public String createTask(String[] command) {
        String result = "";
        for (int i = 0; i < command.length - 1; i++) {
            result += i == command.length - 2
                    ? command[i] : command[i] + " ";
        }
        return result;
    }

    /**
     * Saves the tasks into a file so that they can be loaded on to the taskList
     *
     * @param tasks
     */
    public void saveTask(TaskList tasks) {
        this.createIfNotExist();
        try {
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < tasks.numOfTasks(); i++) {
                String status = tasks.getTask(i).isDone() ? "done" : "undone";
                pw.println(tasks.getTask(i).getTaskName() + " " + status);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
