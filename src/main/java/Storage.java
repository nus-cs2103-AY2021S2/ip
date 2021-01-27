import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    File dukeDataFile;

    public Storage(String filePath) {
        File dataDir = new File("data");
        if (!(dataDir.exists() && dataDir.isDirectory())) {
            if (!dataDir.mkdir()) {
                System.out.println("Data dir not created.");
            }
        }
        dukeDataFile = new File(filePath);
        if (!dukeDataFile.exists()) {
            try {
                if (!dukeDataFile.createNewFile()) {
                    System.out.println("File not created.");
                }
            } catch (IOException e) {
                System.out.println("IOException caught: " + e.getMessage());
            }
        }
    }

    public void readFromStorage(TaskList taskList) {
        try {
            Scanner fileScanner = new Scanner(dukeDataFile);
            while (fileScanner.hasNext()) {
                switch (fileScanner.next()) {
                case "todo":
                    String[] todoArgs = fileScanner.nextLine().split(" ");
                    Task newToDo = new ToDo(todoArgs[0]);
                    if (todoArgs[1].equals("done")) {
                        newToDo.MarkAsDone();
                    }
                    taskList.taskList.add(newToDo);
                    break;

                case "deadline":
                    String[] deadlineArgs = fileScanner.nextLine().split(" ");
                    Task newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                    if (deadlineArgs[2].equals("done")) {
                        newDeadline.MarkAsDone();
                    }
                    taskList.taskList.add(newDeadline);
                    break;

                case "event":
                    String[] eventArgs = fileScanner.nextLine().split(" ");
                    Task newEvent = new Event(eventArgs[0], eventArgs[1]);
                    if (eventArgs[2].equals("done")) {
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
    }
}
