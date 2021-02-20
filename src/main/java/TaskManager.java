import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class TaskManager {
    private final Path filePath;

    public TaskManager(Path filePath){
        this.filePath = filePath;
    }

    private void WriteFile(String fileContent) throws IOException {
        FileWriter fileWriter = new FileWriter(String.valueOf(this.filePath));
        fileWriter.write(fileContent);
        fileWriter.close();
    }

    private ArrayList<String> ReadFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(fileName);
        ArrayList<String> fileContent = new ArrayList<>();

        while(scanner.hasNext()){
            fileContent.add(scanner.nextLine());
            }

        return fileContent;

    }

    public void save(List<? extends Task> list) {
        StringBuilder sb = new StringBuilder();

        list.forEach(x -> sb.append(x).append("\n"));
        try {
            WriteFile(sb.toString());
        } catch (IOException e) {
            System.out.println("Unable to save to disk!");
            e.printStackTrace();
        }

    }



}
