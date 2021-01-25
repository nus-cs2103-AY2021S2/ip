package duke;

import duke.task.Task;
import duke.task.TaskException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String LIST_FILE_PATH = "storage/";
    private static final String LIST_FILE = LIST_FILE_PATH + "list.txt";

    public Storage() throws DukeException {
        //Create directory if not exist
        Path path = Paths.get(LIST_FILE_PATH);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new DukeException("Failed to create storage directory. " + e.getMessage());
        }
    }

    private FileWriter getFileWriter(boolean clearFile) throws DukeException {
        try {
            File file = new File(LIST_FILE);
            FileWriter fw;
            if (file.exists() && !clearFile) {
                fw = new FileWriter(LIST_FILE, true);
            } else {
                fw = new FileWriter(LIST_FILE);
            }
            return fw;
        } catch (IOException e) {
            throw new DukeException("Failed to access file. " + e.getMessage());
        }
    }

    private FileWriter getFileWriter() throws DukeException {
        return getFileWriter(false);
    }

    public ArrayList<Task> readTasksFromFile() throws DukeException {
        File tasks = new File(LIST_FILE);
        Scanner s;
        ArrayList<Task> newList = new ArrayList<>();
        try {
            s = new Scanner(tasks);
            while (s.hasNext()) {
                newList.add(Parser.stringToTask(s.nextLine()));
            }
            s.close();
            return newList;
        } catch (TaskException | FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void writeTaskToFile(Task task) throws DukeException {
        FileWriter fw = getFileWriter();
        try {
            fw.write(task.toString());
            fw.append(System.getProperty("line.separator"));
            fw.close();
        } catch (IOException | NullPointerException e) {
            throw new DukeException("Error writing task to storage/list " + e.getMessage());
        }
    }

    public void clearFile() throws DukeException {
        FileWriter fw = getFileWriter(true);
        try {
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to clear file of contents. " + e.getMessage());
        }
    }
}
