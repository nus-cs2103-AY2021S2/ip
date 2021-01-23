import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class FileManagement {
    public File file;

    public FileManagement(File file) {
        this.file = file;
    }
    public List<String> loadTaskFile() throws IOException {
        this.file.createNewFile();
        Scanner fileScanner = new Scanner(this.file);
        List<String> fileLines = new ArrayList<String>();
        while (fileScanner.hasNext()) {
            String nextTask = fileScanner.nextLine();
            fileLines.add(nextTask);
        }
        return fileLines;
    }

    public void saveTasksToFile(List<String> fileTaskList) {
        try {
            FileWriter fw = new FileWriter(this.file);
            for (int i = 0; i < fileTaskList.size(); i++) {
                fw.write(String.format("%s\n", fileTaskList.get(i)));
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Something went wrong! Not stonks!\n");
        }
    }
}
