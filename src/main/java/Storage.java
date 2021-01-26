import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    //deals with loading tasks from the file and saving tasks in the file
    
    private Path filePath;
    
    public Storage(String strFilePath) {
        this.filePath = Paths.get(strFilePath);
    }

    //Save task data if there is change in task data
    public void save(TaskList tasks) throws ChatException {
        try {
            if (!tasks.equals(load())) {
                String newStr = "";
                for (Task t : tasks.getTaskList()) {
                    newStr += t.allParameterStr() + "\n";
                }
                Files.writeString(filePath, newStr);
            }
        } catch (IOException | ChatException e) {
            throw new ChatException(e.getMessage());
        }
    }

    //load once Chat starts up
    //returns task array from saved data
    public ArrayList<Task> load() throws ChatException {
        try {
            ArrayList<Task> taskListFromFile = new ArrayList<>();
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
                            taskListFromFile.add(new Todo(Boolean.parseBoolean(parArr[1]), parArr[2]));
                        } else if (parArr[0].equals("D")) {
                            taskListFromFile.add(new Deadline(Boolean.parseBoolean(parArr[1]), parArr[2], parArr[3]));
                        } else {
                            //E
                            taskListFromFile.add(new Event(Boolean.parseBoolean(parArr[1]), parArr[2], parArr[3], parArr[4]));
                        }
                    }
                }
            }
            return taskListFromFile;
        } catch (IOException e) {
            throw new ChatException("Error loading file");
        }
    }
    
}
