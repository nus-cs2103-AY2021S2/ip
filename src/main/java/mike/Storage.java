package mike;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exception.MikeCommandExecutionException;

public class Storage {
    private File listFile;

    public Storage(String filePath) {
        this.listFile = new File(filePath);
    }

    /**
     * Update saved tasklist file after command execution
     * Create file if it does not exist
     * @param taskList taskList to be saved
     * @throws MikeCommandExecutionException if tasklist cannot be saved to file
     */
    public void updateListFile(TaskList taskList) throws MikeCommandExecutionException {
        try {
            if (!this.listFile.exists()) {
                this.listFile.getParentFile().mkdir();
                this.listFile.createNewFile();
            }
            FileWriter fw = new FileWriter(this.listFile.getPath());
            fw.write(taskList.toString());
            fw.close();
        } catch (IOException e) {
            throw new MikeCommandExecutionException("Write to saved list error", "File does not exist");
        }
    }

    /**
     * Reads list from saved .txt file
     * @return TaskList object representation of task list saved in file
     */
    public TaskList readListFromFile() {
        TaskList taskList = new TaskList();
        try {
            if (!this.listFile.exists()) {
                this.listFile.getParentFile().mkdir();
                this.listFile.createNewFile();
            }
            Scanner scanner = new Scanner(this.listFile);
            while (scanner.hasNextLine()) {
                taskList.strToTask(scanner.nextLine());
            }
        } catch (MikeCommandExecutionException e) {
            UI.printException(e.getCommand() , e.getMessage());
        } catch (IOException e) {
            UI.printException(e.getMessage());
        }

        return taskList;
    }
}
