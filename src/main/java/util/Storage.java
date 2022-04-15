package util;

import task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static util.Parser.extractCommandString;
import static util.Parser.parseCommandMap;

public class Storage {
    private final String first;
    private final String[] more;

    /**
     * Creates a Storage object instance, which will handle the storing and retrieval
     * of TaskManager objects from the specified save file path.
     *
     * @param first Base directory for the save file.
     * @param more  Additional path directories for the save file.
     */
    public Storage(String first, String... more) {
        this.first = first;
        this.more = more;
    }

    /**
     * Returns the save file specified by the Storage object instance. If an
     * existing save file does not exist in the path provided, automatically
     * creates the required directories and file and returns the resulting File.
     *
     * @return Save file
     * @throws IOException When the save file cannot be read or written to.
     */
    public File getFile() throws IOException {
        Path savePath = Path.of(first, more);
        File file = new File(savePath.toString());
        (new File(savePath.getParent().toString())).mkdir();
        file.createNewFile();
        return file;
    }

    /**
     * Writes the supplied String to the save file. Overwrites any existing lines
     * already present in the file.
     *
     * @param saveString String to be written into the save file.
     * @throws IOException When the save file cannot be written to.
     */
    public void writeToFile(String saveString) throws IOException {
        File file = getFile();
        FileWriter fw = new FileWriter(file);
        fw.write(saveString);
        fw.close();
    }

    /**
     * Returns a TaskManager represented by the information stored in the save file.
     *
     * @return TaskManager object represented by the information stored in the save file.
     * @throws IOException When the save file cannot be read from, or when the
     *                     information inside the save file has been corrupted.
     */
    public TaskManager readTaskManager() throws IOException {
        File file = getFile();
        Scanner sc = new Scanner(file);
        TaskManager taskManager = new TaskManager();

        while (sc.hasNextLine()) {
            String saveLine = sc.nextLine();
            Task newTask = readTask(saveLine);
            taskManager.addTask(newTask);
        }

        return taskManager;
    }

    /**
     * Supporting method for readTaskManager. Converts a saveString into a Task
     *
     * @param saveString SaveString representing the Task saved in disk.
     * @return Task that was represented by the saveString.
     * @throws IOException When a Task cannot be parsed from the saveString due to
     *                     a corrupted saveString.
     */
    private Task readTask(String saveString) throws IOException {
        HashMap<String, List<String>> commandMap = parseCommandMap(saveString);
        String command = extractCommandString(commandMap);
        switch (command) {
        case Todo.COMMAND_STRING:
            return Todo.fromSaveString(saveString);
        case Deadline.COMMAND_STRING:
            return Deadline.fromSaveString(saveString);
        case Event.COMMAND_STRING:
            return Event.fromSaveString(saveString);
        default:
            throw new IOException("Save file cannot be read");
        }
    }

    /**
     * Stores the TaskManager's current state as text in the save file.
     *
     * @param taskManager TaskManager to be saved in the save file.
     * @throws IOException When the save file cannot be written to.
     */
    public void writeTaskManager(TaskManager taskManager) throws IOException {
        String saveString = taskManager.toSaveString();
        writeToFile(saveString);
    }
}
