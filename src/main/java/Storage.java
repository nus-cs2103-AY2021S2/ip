import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final String filePath;
    private TaskList taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void retrieveOrCreate() {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            taskList = readFromFile();
        } else {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void writeToFile(TaskList taskList) {
        String output = "";
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            output += taskList.getTask(i).toString() + "\n";
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(output);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList readFromFile() {
        TaskList taskList = new TaskList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String input = br.readLine();
            String type = "";
            int length = 0;
            int lastIndex = 0;
            String timeWithBracket = "";
            int twbLength = 0;
            int twbLastIndex = 0;
            String year = "";
            String yearLine = "";
            int descLastIndex = 0;
            Task task = null;
            String description = "";
            String date = "";
            String time = "";
            while (input != null) {
                String[] inputArr = input.split(" ");
                type = inputArr[0];
                length = inputArr.length;
                lastIndex = length - 1;
                if (type.equals("[T]")) {
                    for (int i = 2; i < length; i++) {
                        description += inputArr[i] + " ";
                    }
                    task = new Todo(description);
                } else {
                    timeWithBracket = inputArr[lastIndex];
                    String[] twb = timeWithBracket.split("");
                    twbLength = twb.length;
                    twbLastIndex = twbLength - 2;
                    for (int i = 0; i <= twbLastIndex; i++) {
                        time += twb[i];
                    }
                    yearLine = inputArr[lastIndex - 1];
                    String[] yearArr = yearLine.split("");
                    for (int i = 0; i < 4; i++) {
                        year += yearArr[i];
                    }
                    date = inputArr[lastIndex - 3] +
                            " " + inputArr[lastIndex - 2] + " " + year;
                    descLastIndex = lastIndex - 5;
                    for (int i = 2; i <= descLastIndex; i++) {
                        description += inputArr[i] + " ";
                    }
                    if (type.equals("[D]")) {
                        task = new Deadline(description, date, time);
                    } else {
                        task = new Event(description, date, time);
                    }
                }
                if (inputArr[1].equals("[/]")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                description = "";
                date = "";
                time = "";
                year = "";
                input = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }
}
