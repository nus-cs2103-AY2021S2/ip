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
        String strToWrite = "";
        try {
            if (!this.listFile.exists()) {
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
    public TaskList readListFromFile(String filepath){
        TaskList taskList = new TaskList();
        try {
            Scanner scanner = new Scanner(this.listFile);
            while(scanner.hasNextLine()) {
                taskList.strToTask(scanner.nextLine());
            }
        } catch (FileNotFoundException | MikeInvalidInputException e) {
            UI.printResponse(e.getMessage());
        }
        return taskList;
    }
}
