package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * A class for handling saving and loading of data.
 */
public class Storage {
    private File saveFile;

    /**
     * Constructor for <code>Storage</code>.
     *
     * @param filePath a path where the file is to be saved or loaded
     */
    public Storage(String filePath) {
        saveFile = new File(filePath);
        String[] token = filePath.split("/");
        String directoryPath = "";
        for (int i = 0; i < token.length - 1; i++) {
            directoryPath += token[i] + "/";
        }
        File directory = new File(directoryPath);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            saveFile.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Save <code>TaskList</code> by writing to a file.
     * This method will iterate through <code>TaskFile</code> and save it
     * into a text file.
     *
     * @param tasks the TaskList needed to save
     */
    public void save(TaskList tasks) {
        List<Task> list = tasks.getList();
        try {
            FileWriter fileWriter = new FileWriter(saveFile);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                fileWriter.write(task.toSaveFormat());
                fileWriter.write("\n");
                fileWriter.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Return a LinkedList of <code>Task</code> by reading from a file.
     * This method will read through the saved file and return a list of <code>Task</code>.
     *
     * @return LinkedList of Task
     * @throws DukeException
     */
    public LinkedList<Task> load() throws DukeException {
        try {
            LinkedList<Task> list = new LinkedList<>();
            if (!saveFile.exists()) {
                return list;
            }
            Scanner reader = new Scanner(saveFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] token = line.split(" \\| ");
                Task task;
                if (token[0].equals("T")) {
                    task = new Todo(token[2]);
                } else if (token[0].equals("E")) {
                    task = new Event(token[2], token[3], token[4]);
                } else if (token[0].equals("D")) {
                    task = new Deadline(token[2], token[3], token[4]);
                } else {
                    throw new DukeException("Save file is corrupted ): Will be creating a new file");
                }
                if (token[1].equals("1")) {
                    task.setDone();
                }
                list.add(task);
            }
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DukeException("Save file is corrupted ): Will be creating a new file");
        }
        return new LinkedList<>();
    }
}
