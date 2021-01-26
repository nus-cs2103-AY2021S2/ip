package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String directoryPath;
    private String filePath;

    public Storage(String[] filePathArr) {
        String relativeFilePath = "";
        for (int i = 0; i < filePathArr.length - 1; i++) {
            relativeFilePath += filePathArr[i] + File.separator;
        }
        this.directoryPath = System.getProperty("user.dir") + File.separator + relativeFilePath;
        this.filePath = this.directoryPath + File.separator + filePathArr[filePathArr.length - 1];
    }

    public TaskList load() throws DukeException {
        File fileDirectory = new File(this.directoryPath);
        if (!fileDirectory.exists()) {
            System.out.println("hi");
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

    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            writer.write(taskList.saveTaskListString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("File cannot be saved.");
        }
    }
}
