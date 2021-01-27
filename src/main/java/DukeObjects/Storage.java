package DukeObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Encompasses the abstraction of file operations for Duke as Duke.Storage.
 * Stores tasks as "{isDone ? 1 : 0} {command to create task}" in individual lines.
 */
public class Storage {
    private final File file;

    private Storage(File file) {
        this.file = file;
    }

    /**
     * Hard coded static factory creation of java.io.File at path "./data/duke.txt"
     *
     * @param filePath Path of the file to store tasks info from Duke
     * @return Duke.Storage object to handle all storage changes
     */
    public static Storage createStorage(String filePath) {
        // Hard coded check for valid directory
        if (!new File("./data").exists()) {
            new File("./data").mkdir();
        }

        File file = new File(filePath);
        try {
            file.createNewFile();
            return new Storage(file);
        } catch (IOException e) {
            System.out.println("Duke.Storage file cannot be created. List created will not be saved.");
            return null;
        }
    }

    /**
     * Reads and creates the tasks from the file to store in the list. List will not change if file is not found/created.
     */
    public void loadTaskList(TaskList tasks) {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                boolean isDone = reader.nextInt() == 1;
                String command = reader.nextLine();
                Task newTask = Task.dispatchTaskCreation(command);
                if (isDone) {
                    newTask.markDone();
                }
                tasks.add(newTask);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates task specified by the lineNumber according to the type of update.
     *
     * @param lineNumber Refers to the zero-indexed line of the task in the file.
     * @throws IndexOutOfBoundsException if the taskNumber is out of range of the lines in the file.
     */
    public void updateTaskDone(int lineNumber) throws IndexOutOfBoundsException {
        try {
            Scanner reader = new Scanner(file);
            StringBuffer buffer = new StringBuffer();
            int currLine = 0;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (currLine == lineNumber) {
                    line = line.replaceFirst("0", "1");
                }
                buffer.append(line + "\n");
                currLine++;
            }
            reader.close();

            FileWriter writer = new FileWriter(file);
            writer.append(buffer);
            writer.flush();

            if (lineNumber >= currLine || lineNumber < 0) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes the task on the line on lineNumber in this storage's file.
     *
     * @param lineNumber Refers to the zero-indexed line of the task in the file.
     * @throws IndexOutOfBoundsException if the taskNumber is out of range of the lines in the file.
     */
    public void deleteTask(int lineNumber) throws IndexOutOfBoundsException {
        try {
            Scanner reader = new Scanner(file);
            StringBuffer buffer = new StringBuffer();
            int lineNum = 0;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (lineNum != lineNumber) {
                    buffer.append(line + "\n");
                }
                lineNum++;
            }
            reader.close();

            FileWriter writer = new FileWriter(file);
            writer.append(buffer);
            writer.flush();

            if (lineNumber >= lineNum || lineNumber < 0) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Store the task to the back of this storage's file.
     *
     * @param task Duke.Task to be added into the file.
     */
    public void storeTask(String task) throws IndexOutOfBoundsException {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append("0 " + task + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
