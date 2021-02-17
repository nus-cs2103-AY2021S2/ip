import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private final String dirName;
    private final String fileName;

    public Storage(String filePath) { // split into directory and filename
        int index = filePath.lastIndexOf("/");
        if (index == -1) {
            dirName = "";
            fileName = filePath;
        } else {
            dirName = filePath.substring(0, index + 1); // e.g. "data/"
            fileName = filePath.substring(index + 1); // e.g. "duke.txt"
        }
    }

    private Task loadParser(String taskStringFromFile) {
        Task t;
        String taskIsDone, taskString;

        taskIsDone = new SplitString(taskStringFromFile, ",").getFirstString(); // e.g. "1"
        taskString = new SplitString(taskStringFromFile, ",").getSecondString(); // e.g. "todo randomTask"
        t = Parser.parseStringToTask(taskString);
        if (taskIsDone.equals("1")) {
            t.markAsDone();
        }

        return t;
    }

    public void save(TaskList taskList) {
        File taskDir, taskFile;

        // Check if directory and file exist, create if not
        try {
            taskDir = new File(dirName);
            if (!taskDir.isDirectory()) {
                taskDir.mkdir();
            }
            taskFile = new File(dirName + fileName);
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create string to write to file
        String stringToWrite = "";
        Task t;
        int count = 0;
        while (count < taskList.numTask) {
            t = taskList.tasks[count];
            stringToWrite += Parser.parseTaskToString(t) + "\n";
            count++;
        }

        // Write string to file
        try {
            FileWriter taskFileWriter = new FileWriter(dirName + fileName);
            taskFileWriter.write(stringToWrite);
            taskFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Task[] load() throws Exception {
        Task[] tasks = new Task[100];
        int numTask = 0;

        File taskFile = new File(dirName + fileName);
        Scanner taskFileScanner = new Scanner(taskFile);
        while (taskFileScanner.hasNextLine()) {
            tasks[numTask] = loadParser(taskFileScanner.nextLine());
            numTask++;
        }

        return tasks;
    }

}
