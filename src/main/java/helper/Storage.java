package helper;

import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        try {
            File myFile = new File("duke.txt");
            Scanner myScanner = new Scanner(myFile);
        } catch (Exception e) {
            throw new DukeException("No such file");
        }
        return null;
    }

    public void saveFile(TaskList taskList) throws DukeException {
        try {
            File myFile = new File("duke.txt");
            if (myFile.exists()) {
                myFile.delete();
            } else {
                myFile.createNewFile();
            }
            FileWriter myWriter = new FileWriter("duke.txt");
            for (Task t: taskList.getTaskList()) {
                myWriter.write(t.toString() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            throw new DukeException("Cannot save file");
        }


    }

}
