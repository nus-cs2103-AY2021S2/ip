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

}
