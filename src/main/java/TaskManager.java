import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private final Path filePath;

    private TaskManager(Path filePath) {
        this.filePath = filePath;
    }



    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(filePath));
        fw.write(textToAdd);
        fw.close();
    }

    private List<String> readFromFile() throws FileNotFoundException {
        File f = new File(String.valueOf(filePath));
        List<String> taskStringList = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            taskStringList.add(s.nextLine());
        }
        return taskStringList;
    }

    public void save(List<? extends Task> list) {
        StringBuilder sb = new StringBuilder();
        //List<String> encodedList = TaskEncoder.encodeTaskList(list);
       // encodedList.forEach(x -> sb.append(x).append("\n"));
        try {
            writeToFile(sb.toString());
        } catch (IOException e) {
            System.out.println("Unable to save to disk!");
            e.printStackTrace();
        }

    }


}