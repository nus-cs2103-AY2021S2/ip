import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileManager {

    public FileManager() {}

    public boolean createFile(String path) throws IOException {
        File data = new File(path);
        return data.createNewFile();
    }

    public void initialiseDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public void writeToFile(String path, String message) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(message);
        fileWriter.close();
    }

    public StringBuilder loadTasksFromFile(String path) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        File data = new File(path);
        Scanner sc = new Scanner(data);

        while (sc.hasNextLine()) {
            String value = sc.nextLine();
            sb.append(value);
            sb.append("\n");
        }
        return sb;

    }
}
