import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class SaveToFile {
    FileWriter fio;

    public SaveToFile() {
        File file = initiateFile();
        try {
            this.fio = new FileWriter(file);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public File initiateFile () {
        String home = System.getProperty("user.home");

        // inserts correct file path separator on *nix and Windows
        // works on *nix
        // works on Windows
        Path path = java.nio.file.Paths.get(home, "iP", "data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        File file = null;
        try {
            if (!directoryExists) {
                java.nio.file.Files.createDirectories(path);
                System.out.println("Path Created: " + path);
            } else {
                System.out.println("Path exits");
            }
            String filePath = path + File.separator + "Duke.txt";
            file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File exits");
            }
        } catch (IOException e) {
            System.out.println("Failed to create File");
            e.printStackTrace();
        }
        return file;
    }

    public void writeToFile(Task task) {
        String data;
        data = task.getClass().toString().substring(6) + " | " + task.getState().toString() + " | " + task.getInput();
        try {
            this.fio.write(data + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void closeFile () {
        try {
            this.fio.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
