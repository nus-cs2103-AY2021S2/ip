import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {

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

    private static void saveFile(List<Task> lt) throws DukeException {
        try {
            File myFile = new File("duke.txt");
            if (myFile.exists()) {
                myFile.delete();
            } else {
                myFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Cannot save file");
        }
    }

}
