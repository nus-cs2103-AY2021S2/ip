import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private final String DATA_FOLDER_PATH = "data";
    private final String DUKE_FILE_PATH = "data/duke.txt";

    public Storage() { }

    public void loadData() throws FileNotFoundException {
        File dataFolder = new File(DATA_FOLDER_PATH);
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File dukeFile = new File(DUKE_FILE_PATH);
        if (dukeFile.exists()) {
            Scanner s = new Scanner(dukeFile); // create a Scanner using the File as the source
            while (s.hasNext()) {
                Duke.readTaskList(s.nextLine());
            }
            Duke.printList();
        }
    }

    public void writeNewFile(String userInput) throws IOException {
        FileWriter fw = new FileWriter(DUKE_FILE_PATH);
        fw.write(userInput);
        fw.close();
    }

}
