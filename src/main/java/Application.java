import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {

    private FileManager fileManager;
    private TaskManager taskManager;

    private String dataDirectoryName;
    private String dataFileName;

    public Application() {
        fileManager = new FileManager();
        taskManager = new TaskManager();
        dataDirectoryName = "../../../data/";
        dataFileName = "data.txt";
    }

    public void initialiseDataStorage() {
        try {
            fileManager.initialiseDirectory(dataDirectoryName);
            fileManager.createFile(dataDirectoryName + dataFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTasksFromStorage() {
        try {
            StringBuilder tasks = fileManager.loadTasksFromFile(dataDirectoryName + dataFileName);
            taskManager.loadTasksFromStorage(tasks.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
