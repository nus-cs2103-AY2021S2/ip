import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    Storage(String filePath){
        this.filePath = filePath;
    }

    public Scanner loadFile() throws FileNotFoundException {
        File file = new File(this.filePath);
        return new Scanner(file);
    }

    public void writeTasksToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(task.toString());
        fw.close();
    }
}
