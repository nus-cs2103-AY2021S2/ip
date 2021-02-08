package duke.util;

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

    /**
     * create directory locally if it doesn't exist yet
     * @param filePath The name of the folder
     */
    private String makeDirectory(String filePath) {
        assert(filePath!=null);
        File file = new File(filePath);
        Boolean bool = file.mkdirs();
        System.out.println(bool);
        return filePath;
    }

    /**
     * read previous save of List<Task> if it exist.
     */
    public List<Task> readPreviousFile() {
        List<Task> listOfTasks = new ArrayList<>();
        try {
            listOfTasks = Parser.parseResult(scanFile());
        } catch (Exception e) {
            System.out.println(e);
//            System.out.println("Looks like you don't have any previous file. We will create a new Task Manager for you!");
        } finally {
            return listOfTasks;
        }
    }

    /**
     * save current TaskList locally
     * @param taskList The task list we would like to save
     */
    public void saveTasks(TaskList taskList) {
        String taskToSave = Parser.convertTasksToString(taskList);
        try {
            FileWriter writer = new FileWriter(this.filePath + this.fileName);
            writer.write(taskToSave);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * scan the .txt file that we have saved previously. helper function of readPreviousFile() function
     */
    private List<String> scanFile() throws FileNotFoundException {
        List<String> fileInString = new ArrayList<>();
        assert(filePath!=null);
        assert(fileName!=null);
        File file = new File(this.filePath + this.fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            fileInString.add(scanner.nextLine());
        }
        return fileInString;
    }

}
