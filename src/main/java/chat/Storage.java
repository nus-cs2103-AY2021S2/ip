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
            ArrayList<Task> tasksFromFile = new ArrayList<>();
            if (!Files.exists(filePath)) {
                if (!Files.exists(filePath.getParent())) {
                    Files.createDirectory(filePath.getParent());
                }
                Files.createFile(filePath);
            } else {
                String lines = Files.readString(filePath);
                if (!lines.isEmpty()) {
                    String[] strArr = lines.split("\n");
                    for (String s : strArr) {
                        String[] parArr = s.split(","); //parameter array
                        if (parArr[0].equals("T")) {
                            tasksFromFile.add(new Todo(Boolean.parseBoolean(parArr[1]), parArr[2]));
                        } else if (parArr[0].equals("D")) {
                            tasksFromFile.add(new Deadline(Boolean.parseBoolean(parArr[1]), parArr[2], parArr[3]));
                        } else {
                            //E
                            tasksFromFile.add(new Event(Boolean.parseBoolean(parArr[1]), parArr[2], parArr[3], parArr[4]));
                        }
                    }
                }
            }
            return tasksFromFile;
        } catch (IOException e) {
            throw new ChatException("Error loading file");
        }
    }
    
}
