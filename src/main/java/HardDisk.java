import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HardDisk {
    private static Path filePath;

    public static void retrieveFilePath() {
        try {
            String current = System.getProperty("user.dir");
            Path projectRoot = Paths.get(current).getParent();

            Path directoryPath = Paths.get(projectRoot.toString(),"data");
            Boolean directoryExists = Files.exists(directoryPath);

//            System.out.println(directoryPath);


            if (directoryExists.equals(false)) {
                Files.createDirectory(directoryPath);
            }

            Path file = Paths.get(directoryPath.toString(), "Baron.txt");
            Boolean fileExists = Files.exists(file);
            if (fileExists.equals(false)) {
                Files.createFile(file);
            }
            filePath = file;
        } catch (IOException e) {
            System.out.println("directory/file is already created.");
        }
    }

    public static void save(ArrayList<Task> taskList) {
        try {
            String data = "";

            for(int i = 0; i < taskList.size(); i++) {
                data = data + taskList.get(i).encode() + "\n";

//                System.out.println("\ncheck file contents");
//                List s = Files.readAllLines(filePath);
//                for (int j = 0; j < s.size(); j++) {
//                    System.out.println(s.get(j));
//                }
            }

            Files.write(filePath, data.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {

        }
    }

    public static TaskList read() {
        TaskList taskList = new TaskList();
        try {
            retrieveFilePath();
            List<String> s = Files.readAllLines(filePath);
//            System.out.println(s.size());

            for (int i = 0; i < s.size() ; i++) {
                ListParser p = new ListParser();
                p = p.parse(s.get(i));
                taskList.populate(p);
            }

        } catch (IOException e) {
            System.out.println("error here2");

        }

        return taskList;
    }
}
