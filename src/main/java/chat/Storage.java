package chat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import chat.task.Task;
import chat.task.Todo;
import chat.task.Deadline;
import chat.task.Event;

/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    
    private Path filePath;

    /**
     * Initialises Storage object.
     * 
     * @param strFilePath Path of the file that is read and written to.
     */
    public Storage(String strFilePath) {
        this.filePath = Paths.get(strFilePath);
    }

    /**
     * If there is a change in data from the file, this method saves the new data to the file.
     * 
     * @param taskList TaskList object that contains a list of tasks.
     * @throws ChatException If there is error loading file or file cannot be found.
     */
    public void save(TaskList taskList) throws ChatException {
        try {
            if (!taskList.equals(load())) {
                String newStr = "";
                for (Task t : taskList.getTasks()) {
                    newStr += t.allParameterStr() + "\n";
                }
                Files.writeString(filePath, newStr);
            }
        } catch (IOException | ChatException e) {
            throw new ChatException(e.getMessage());
        }
    }

    /**
     * Loads data of tasks from file and stores them in a list of tasks.
     * <p>An empty list of tasks is created if file is empty.</p>
     * 
     * @return List of tasks loaded from data in file.
     * @throws ChatException If there is error loading file or file cannot be found.
     */
    public ArrayList<Task> load() throws ChatException {
        try {
            createFileAndOrDirectoryIfDontExist();
            String lines = Files.readString(filePath);
            ArrayList<Task> tasksFromFile = createTaskList(lines);
            return tasksFromFile;
        } catch (IOException e) {
            throw new ChatException("Error loading file");
        }
    }
    
    private void createFileAndOrDirectoryIfDontExist() throws IOException {
        if (!Files.exists(filePath)) {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectory(filePath.getParent());
            }
            Files.createFile(filePath);
        }
    }
    
    private static Task convertStrToTaskObject(String str) throws ChatException {
        String[] parameters = str.split(",");
        String taskType = parameters[0];
        
        switch(taskType) {
        case "T":
            return new Todo(Boolean.parseBoolean(parameters[1]), parameters[2]);
        case "D":
            return new Deadline(Boolean.parseBoolean(parameters[1]), parameters[2], parameters[3]);
        case "E": 
            return new Event(Boolean.parseBoolean(parameters[1]), parameters[2], parameters[3], parameters[4]);
        default:
            throw new ChatException("Incorrect data format in file.");
        }
    }
    
    private static ArrayList<Task> createTaskList(String lines) throws ChatException { 
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            if (!lines.isEmpty()) {
                String[] strArr = lines.split("\n");
                for (String s : strArr) {
                    tasks.add(convertStrToTaskObject(s));
                }
            }
            return tasks;
        } catch (ChatException e) {
            throw e;
        }
    }
    
}
