import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private String fileName;

    public Storage(String filePath, String fileName) {
        filePath = makeDirectory(filePath);
        this.filePath = filePath;
        this.fileName = fileName;
    }

    private String makeDirectory(String filePath) {
        File file = new File(filePath);
        Boolean bool = file.mkdirs();
        System.out.println(bool);
        return filePath;
    }

    public List<Task> readPreviousFile() {
        List<Task> listOfTasks = new ArrayList<>();
        try {
            listOfTasks = Parser.parseResult(scanFile());
        } catch (FileNotFoundException e) {
            System.out.println("Looks like you don't have any previous file. We will create a new Task Manager for you!");
        }
        return listOfTasks;
    }

    public void saveTasks(TaskManager taskManager) {
        String taskToSave = Parser.convertTasksToString(taskManager);
        try {
            FileWriter writer = new FileWriter(this.filePath + this.fileName);
            writer.write(taskToSave);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> scanFile() throws FileNotFoundException {
        List<String> fileInString = new ArrayList<>();
        File file = new File(this.filePath + this.fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            fileInString.add(scanner.nextLine());
        }
        return fileInString;
    }

}
