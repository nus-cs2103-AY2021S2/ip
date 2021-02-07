package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.TaskType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Scanner;

/**
 * Represents file storage & deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    protected String filePath;

    /**
     * Creates a new instance of <code>Storage</code> at the specified file path.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the file at the given file path.
     *
     * @return Task list of existing tasks in file.
     * @throws DukeException If file path does not exist.
     * @throws IOException If there are input or output issues.
     */
    public TaskList load() throws DukeException, IOException {
        Path path = Path.of(this.filePath);
        if (Files.notExists(path)) {
            this.createFile();
            throw new DukeException("File path does not exist! "
                    + "A new file has been created according to given file path.");
        } else {
            return this.textFileToArrayList();
        }
    }

    /**
     * Creates a new folder and new file at the given file path.
     *
     * @throws IOException
     */
    public void createFile() throws IOException {
        String folderPath = this.filePath.substring(0, this.filePath.lastIndexOf("/") + 1);
        File folder = new File(folderPath);
        folder.mkdirs();

        File file = new File(filePath);
        file.createNewFile();
    }

    /**
     * Append text to existing file.
     *
     * @param filePath     Path of file.
     * @param textToAppend Text to append to existing file.
     * @throws IOException If there are input or output errors.
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        Path path = Path.of(filePath);
        if (Files.notExists(path)) {
            this.createFile();
        }
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Reads text file and returns task list with existing tasks in text file.
     *
     * @return Task list of existing tasks in file.
     * @throws IOException   If there are input or output errors.
     * @throws DukeException If description of task is not in the correct format.
     */
    public TaskList textFileToArrayList() throws IOException, DukeException {
        File f = new File(this.filePath);
        Scanner sc = new Scanner(f);
        TaskList taskList = new TaskList();

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            String taskTypeLetter = Character.toString(nextLine.charAt(1));
            if (taskTypeLetter.equals("T")) {
                taskList.addTask(TaskType.TODO, nextLine.substring(7), true, this);
            } else if (taskTypeLetter.equals("D")) {
                String[] nextLineArr = nextLine.substring(7).split(" \\(by: ");
                String description = nextLineArr[0] + " /by "
                        + nextLineArr[1].substring(0, nextLineArr[1].lastIndexOf(")"));
                taskList.addTask(TaskType.DEADLINE, description, true, this);
            } else if (taskTypeLetter.equals("E")) {
                String[] nextLineArr = nextLine.substring(7).split(" \\(at: ");
                String description = nextLineArr[0] + " /at "
                        + nextLineArr[1].substring(0, nextLineArr[1].lastIndexOf(")"));
                taskList.addTask(TaskType.EVENT, description, true, this);
            }
        }

        return taskList;
    }
}
