package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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
     * @throws DukeException If file for storage cannot be created.
     */
    public TaskList openFile() throws DukeException {
        File file = new File(path);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdir();
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("File cannot be created.");
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
            throw new DukeException("File cannot be found.");
        }
    }

    /**
     * Retrieves a task by processing the content stored locally.
     * @param txtLine A line of text stored in the txt file.
     * @return Task based on the file content.
     * @throws DukeException If text is invalid.
     */
    public Task processContent (String txtLine) throws DukeException {
        String[] content = txtLine.split(" \\| ");
        String taskType = content[0];
        int taskStatus = Integer.parseInt(content[1]);
        String taskDescription = content[2];

        switch(taskType) {
        case "D":
            return new Deadline(taskDescription, taskStatus, LocalDateTime.parse(content[3],
                    DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")));
        case "E":
            return new Event(taskDescription, taskStatus, LocalDateTime.parse(content[3],
                    DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")));
        case "T":
            return new Todo(taskDescription, taskStatus);
        default:
            throw new DukeException(taskType + " is an invalid text type. Please modify the file accordingly.");
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
