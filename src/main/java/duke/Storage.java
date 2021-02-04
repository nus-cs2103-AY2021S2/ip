package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a Storage object which can perform
 * saving and loading functions.
 */
public class Storage {
    private String directoryPath;
    private String filePath;

    /**
     * Returns a Storage object which performs saving and loading functions.
     *
     * @param filePathArr an Array with the relative file path.
     * eg. "data/duke.txt" would be ["data", "duke.txt"]
     */
    public Storage(String[] filePathArr) {
        String relativeFilePath = "";
        for (int i = 0; i < filePathArr.length - 1; i++) {
            relativeFilePath += filePathArr[i] + File.separator;
        }
        this.directoryPath = System.getProperty("user.dir") + File.separator + relativeFilePath;
        this.filePath = this.directoryPath + File.separator + filePathArr[filePathArr.length - 1];
    }

    /**
     * Returns a TaskList loaded from the defined filePath
     * If filepath does not exist, returns a TaskList with an empty
     * list of Tasks.
     *
     * @return TaskList loaded from file
     * @throws DukeException if the file is not accessible, or cannot be created.
     */
    public TaskList load() throws DukeException {
        File fileDirectory = new File(this.directoryPath);
        if (!fileDirectory.exists()) {
            fileDirectory.mkdirs();
        }
        File file = new File(this.filePath);
        TaskList taskList = new TaskList();
        try {
            file.createNewFile();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                taskList.addTask(Parser.parseTaskFromFile(line));
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("The tasks file is not accessible.");
        } catch (IOException e) {
            throw new DukeException(this.filePath + " cannot be created.");
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Saves the tasks to the file with defined filepath.
     * If the TaskList is empty, saves an empty file.
     *
     * @param taskList Tasklist with the Tasks to be saved
     * @throws DukeException if the file cannot be saved or written to.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            writer.write(taskList.saveTaskListString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("File cannot be saved or written to.");
        }
    }
}
