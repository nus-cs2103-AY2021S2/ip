package monica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import monica.task.Deadline;
import monica.task.Event;
import monica.task.Task;
import monica.task.TaskList;
import monica.task.Todo;
/**
 * Represents local storage of task data.
 */
public class Storage {
    private String path;
    private TaskList taskList = new TaskList();

    /**
     * Constructor for Storage.
     * @param path Path of the file storing task data.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads and opens the file with a specified path.
     * @return Task list.
     * @throws MonicaException If file for storage cannot be created.
     */
    public TaskList openFile() throws MonicaException {
        File file = new File(path);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdir();
                file.createNewFile();
            } catch (IOException e) {
                throw new MonicaException("File cannot be created.");
            }
        }

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String txtLine = sc.nextLine();
                Task task = processContent(txtLine);
                taskList.addTask(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new MonicaException("File cannot be found.");
        }
    }

    /**
     * Retrieves a task by processing the content stored locally.
     * @param txtLine A line of text stored in the txt file.
     * @return Task based on the file content.
     * @throws MonicaException If text is invalid.
     */
    public Task processContent (String txtLine) throws MonicaException {
        String[] content = txtLine.split(" \\| ");
        String taskType = content[0];
        int taskStatus = Integer.parseInt(content[1]);
        String taskDescription = content[2];
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");

        switch(taskType) {
        case "D":
            String deadlineTime = content[3];
            return new Deadline(taskDescription, taskStatus,
                    LocalDateTime.parse(deadlineTime, dateTimeFormat));
        case "E":
            String eventTime = content[3];
            return new Event(taskDescription, taskStatus,
                    LocalDateTime.parse(eventTime, dateTimeFormat));
        case "T":
            return new Todo(taskDescription, taskStatus);
        default:
            throw new MonicaException(taskType + " is an invalid text type.");
        }
    }

    /**
     * Updates the local file every time there is a change in the task list.
     */
    public void updateFile() {
        try {
            FileWriter fw = new FileWriter(path);
            for (Task t : taskList.getTasks()) {
                fw.write(t.toTxt());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File cannot be found");
        }
    }
}
