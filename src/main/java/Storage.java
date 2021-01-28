import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    private final String filePath;
    private final String fileName;

    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public void saveTaskList(String tasks) {
        try {
            FileWriter fw = new FileWriter(filePath + fileName);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            System.out.println("This file cannot be saved... much like you!");
        }
    }

    public void loadTaskList(TaskList taskList) {
        try {
            File f = new File(filePath + fileName);
            f.createNewFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                String[] taskArr = taskString.split("\\| ");
                Task task = null;
                switch(taskArr[0]) {
                    case "T ":
                        String input = "todo " + taskArr[2];
                        task = new Todo(input);
                        break;
                    case "D ":
                        LocalDateTime dateTime = LocalDateTime.parse(taskArr[3].substring(4), DateTimeFormatter.ofPattern("dd-M-yyyy Hmm"));
                        task = new Deadline(dateTime, taskArr[2].substring(0, taskArr[2].length() - 1));
                        break;
                    case "E ":
                        LocalDateTime dateTimeStart = LocalDateTime.parse(taskArr[3].substring(4), DateTimeFormatter.ofPattern("dd-M-yyyy Hmm"));
                        task = new Event(dateTimeStart, taskArr[2].substring(0, taskArr[2].length() - 1));
                        break;
                }
                if (task != null) {
                    if (taskArr[1].equals("X ")) {
                        task.checkTask();
                    }
                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("This file cannot be loaded... My apologies");
        }
    }
}
