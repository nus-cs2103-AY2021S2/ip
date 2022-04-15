package duke.storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents a storage object that responsible for the read and save tasks file for Duke.
 */
public class Storage {
    private boolean isFileCreated = true;
    private final String filePath;
    private boolean isDirCreated = true;
    private final String dirPath;


    /**
     * Constructor for Storage object
     *
     * @param filePath The file path of the txt file that stores the tasks. eg. ./data/duke.txt
     * @param dirPath The directory path of the txt file. eg. ./data
     */
    public Storage(String filePath, String dirPath) {
        this.filePath = filePath;
        this.dirPath = dirPath;
    }

    /**
     * The function reads the txt file based on the file path and
     * directory path and then returns the taskList.
     *
     * @param taskList The taskList that will be used in the program, usually an empty list
     *                 and then the tasks in the txt file will be added into this empty list.
     * @throws FileNotFoundException if the program cannot find the file then it will throw the exception.
     * @return a taskList object that tasks in the txt file is added into.
     */
    public TaskList readTasks(TaskList taskList) throws FileNotFoundException {
        File f = new File(dirPath);
        if (!f.exists()) {
            isDirCreated = f.mkdir();
        }

        assert isDirCreated : "The directory is not properly created! Please contact the developer.";

        try {
            f = new File(filePath);
            if (!f.exists()) {
                isFileCreated = f.createNewFile();
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }

        assert isFileCreated : "The txt file is not properly created! Please contact the developer.";

        Scanner s = new Scanner(f);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        while (s.hasNext()) {
            String info = s.nextLine();
            boolean status = Integer.parseInt(info.substring(0, 1)) == 1;
            String type = info.substring(3, 4);
            int priority = Integer.parseInt(info.substring(info.length() - 1));

            if (type.equals("T")) {
                String name = info.substring(5, info.length() - 1);
                ToDo todo = new ToDo(name, status, priority);
                taskList.addTask(todo);
            } else if (type.equals("D")) {
                int endNameIndex = info.indexOf("(");
                int endTimeIndex = info.length() - 2;
                String name = info.substring(5 , endNameIndex - 1);
                String by = info.substring(endNameIndex + 5, endTimeIndex);
                LocalDateTime byTime = LocalDateTime.parse(by, df);
                Deadline deadline = new Deadline(name, byTime, status, priority);
                taskList.addTask(deadline);
            } else if (type.equals("E")) {
                int endNameIndex = info.indexOf("(");
                int endTimeIndex = info.length() - 2;
                String name = info.substring(5 , endNameIndex - 1);
                String at = info.substring(endNameIndex + 5, endTimeIndex);
                LocalDateTime atTime = LocalDateTime.parse(at, df);
                Event event = new Event(name, atTime, status, priority);
                taskList.addTask(event);
            }
        }
        return taskList;
    }


    /**
     * The function saves the tasks immediately after the program terminates into the txt file into the
     * path that the user specified.
     *
     * @param taskList The taskList that will be used in the program.
     * @throws IOException if the program meets IO problem.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);

        StringBuilder builder = new StringBuilder();
        if (taskList.getNumOfTasks() == 0) {
            String textToAppend = builder.toString();
            fw.write(textToAppend);
            fw.close();
        } else {
            for (Task task:taskList.getTasks()) {
                int status = task.getStatus() ? 1 : 0;
                int priority = task.getPriority();
                builder.append(status);
                builder.append("|");
                String taskName = task.toString();
                builder.append(taskName);
                builder.append(priority);
                builder.append("\n");
            }
            String textToAppend = builder.toString();
            fw.write(textToAppend);
            fw.close();
        }
    }
}
