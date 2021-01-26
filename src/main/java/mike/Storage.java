package mike;

import exception.MikeInvalidInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {
    private File listFile;

    public Storage(String filePath) {
        this.listFile = new File(filePath);
    }

    public void updateListFile(TaskList taskList) throws MikeInvalidInputException {
        try {
            if (!this.listFile.exists()) {
                this.listFile.getParentFile().mkdir();
                this.listFile.createNewFile();
            }
            FileWriter fw = new FileWriter(this.listFile.getPath());
            fw.write(taskList.toString());
            fw.close();
        } catch (IOException e) {
            throw new MikeInvalidInputException("File does not exist");
        }
    }

    /* TODO catch exceptions better */
    public TaskList readListFromFile(){
        TaskList taskList = new TaskList();
        try {
            if (!this.listFile.exists()) {
                this.listFile.getParentFile().mkdir();
                this.listFile.createNewFile();
            }
            Scanner scanner = new Scanner(this.listFile);
            while(scanner.hasNextLine()) {
                taskList.strToTask(scanner.nextLine());
            }
        } catch (MikeInvalidInputException | IOException e) {
            UI.printResponse(e.getMessage());
        }
        return taskList;
    }
}
