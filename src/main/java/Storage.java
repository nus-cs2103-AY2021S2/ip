import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    File dukeDataFile;

    public Storage(String filePath) {
        Path path = Paths.get(filePath);
        dukeDataFile = new File(filePath);
        if (!Files.exists(path)) {
            try {
                dukeDataFile.createNewFile();
            } catch (IOException e) {
                try {
                    Path dirPath = Paths.get("data/");
                    Files.createDirectories(dirPath);
                    dukeDataFile.createNewFile();
                } catch (IOException ex) {
                    System.out.println("IOException caught: " + ex.getMessage());
                }
            }
        }
    }

    public void readFromStorage(TaskList taskList) {
        try {
            Scanner fileScanner = new Scanner(dukeDataFile);
            while (fileScanner.hasNext()) {
                String[] taskArgs = fileScanner.nextLine().split(" ", 2);
                switch (taskArgs[0]) {
                case "todo":
                    taskArgs = taskArgs[1].split(" ", 2);
                    Task newToDo = new ToDo(taskArgs[1]);
                    if (taskArgs[0].equals("done")) {
                        newToDo.MarkAsDone();
                    }
                    taskList.taskList.add(newToDo);
                    break;

                case "deadline":
                    taskArgs = taskArgs[1].split(" ", 4);
                    Task newDeadline = new Deadline(taskArgs[3], taskArgs[0] + " " + taskArgs[1]);
                    if (taskArgs[2].equals("done")) {
                        newDeadline.MarkAsDone();
                    }
                    taskList.taskList.add(newDeadline);
                    break;

                case "event":
                    taskArgs = taskArgs[1].split(" ", 4);
                    Task newEvent = new Event(taskArgs[3], taskArgs[0] + " " + taskArgs[1]);
                    if (taskArgs[2].equals("done")) {
                        newEvent.MarkAsDone();
                    }
                    taskList.taskList.add(newEvent);
                    break;

                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public void writeToStorage(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(dukeDataFile);
        taskList.taskList.forEach(task -> {
            try {
                fw.write(task.generateDataString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("IOException thrown.");
            }
        });
        fw.close();
    }
}
