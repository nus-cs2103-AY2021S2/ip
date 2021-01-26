import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String pathString;
    public StringBuffer stringBufferOfData;
    public int numTasks;

    public Storage(String pathString) {
        this.pathString = pathString;
        stringBufferOfData = new StringBuffer();
        numTasks = 0;
    }

    public void createFile() throws IOException {
        Path dirPath = Paths.get(System.getProperty("user.dir") + "/data");
        Files.createDirectories(dirPath);
        Files.createFile(Paths.get(pathString));
    }

    public void loadFileContents(ArrayList<Task> tasks) throws FileNotFoundException {
        File file = new File(pathString);
        Scanner scanFile = new Scanner(file);

        while (scanFile.hasNext()) {
            String fileData = scanFile.nextLine();
            if (numTasks <= 0) {
                stringBufferOfData.append(fileData);
            } else {
                stringBufferOfData.append("\n").append(fileData);
            }

            String[] dataArr = fileData.split(" \\| ", 4);
            String taskType = dataArr[0];
            String isDone = dataArr[1];
            String desc = dataArr[2];

            switch (taskType) {
                case "T":
                    ToDos newTodo = new ToDos(desc, isDone.equals("1"));
                    tasks.add(newTodo);
                    numTasks++;
                    break;
                case "D":
                    LocalDate by = LocalDate.parse(dataArr[3]);
                    Deadlines newDeadline = new Deadlines(desc, by, isDone.equals("1"));
                    tasks.add(newDeadline);
                    numTasks++;
                    break;
                case "E":
                    LocalDate at = LocalDate.parse(dataArr[3]);
                    Events newEvent = new Events(desc, at, isDone.equals("1"));
                    tasks.add(newEvent);
                    numTasks++;
                    break;
            }
        }
    }

    public void addToFile(String data) throws IOException {
        FileWriter fw;
        if (numTasks <= 0) {
            fw = new FileWriter(pathString);
            fw.write(data);
            stringBufferOfData.append(data);
        } else {
            fw = new FileWriter(pathString, true);
            fw.write(System.lineSeparator() + data);
            stringBufferOfData.append("\n").append(data);
        }

        fw.close();
    }

    public void modifyFile(String before, String after) throws IOException {
        int startIndex = stringBufferOfData.indexOf(before);
        int endIndex = startIndex + before.length();
        stringBufferOfData.replace(startIndex, endIndex, after);

        FileWriter fw = new FileWriter(pathString);
        fw.write(stringBufferOfData.toString());
        fw.close();
    }

    public void deleteFromFile(String data) throws IOException {
        int startIndex = stringBufferOfData.indexOf(data);
        int endIndex = startIndex + data.length() + 1;
        stringBufferOfData.delete(startIndex, endIndex);

        FileWriter fw = new FileWriter(pathString);
        fw.write(stringBufferOfData.toString());
        fw.close();
    }
}
