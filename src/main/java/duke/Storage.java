package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Task;

/**
 * Encompasses the abstraction of file operations for Duke as a Storage.
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
     * @param filePath Path of the file to store the info.
     * @return Storage object to handle all storage changes.
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
            System.out.println("Storage file cannot be created. List created will not be saved.");
            return null;
        }
    }

    /**
     * Reads and creates the tasks from the file to store in the list. List will not change if file is not
     * found/created.
     */
    public void loadTaskList(TaskList tasks) {
        Scanner reader;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

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
    }

    /**
     * Updates task specified by the targetLineNumber to be done.
     *
     * @param targetLineNumber Refers to the zero-indexed line of the task in the file to be updated.
     * @throws IndexOutOfBoundsException if the taskNumber is out of range of the lines in the file.
     */
    public void updateTaskDone(int targetLineNumber) throws IndexOutOfBoundsException {
        Scanner reader;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        StringBuffer buffer = new StringBuffer();
        int currentLineNumber = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            // Replace first character indicating isDone of task if currentLine is the targetLine.
            if (currentLineNumber == targetLineNumber) {
                line = line.replaceFirst("0", "1");
            }
            // Append all lines
            buffer.append(line + "\n");
            currentLineNumber++;
        }
        reader.close();

        if (targetLineNumber >= currentLineNumber || targetLineNumber < 0) {
            throw new IndexOutOfBoundsException();
        }
        // lineNumber is valid, then write to file
        writeCharSequenceToFile(file, buffer);
    }

    /**
     * Deletes the task on the line on targetLineNumber in this storage's file.
     *
     * @param targetLineNumber Refers to the zero-indexed line of the task in the file to be deleted.
     * @throws IndexOutOfBoundsException if the taskNumber is out of range of the lines in the file.
     */
    public void deleteTask(int targetLineNumber) throws IndexOutOfBoundsException {
        Scanner reader;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        StringBuffer buffer = new StringBuffer();
        int currentLineNumber = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            // Append all lines except the line that matches lineNumber
            if (currentLineNumber != targetLineNumber) {
                buffer.append(line + "\n");
            }
            currentLineNumber++;
        }
        reader.close();

        if (targetLineNumber >= currentLineNumber || targetLineNumber < 0) {
            throw new IndexOutOfBoundsException();
        }
        // lineNumber is valid, then write to file.
        writeCharSequenceToFile(file, buffer);
    }

    /**
     * Store the command that creates a task to the back of this storage's file,
     * such that it can be called to create a Task directly upon an instantiation of storage.
     *
     * @param command Command that creates a Task to be added into the file.
     */
    public void storeTaskCommand(String command) {
        try {
            FileWriter writer = new FileWriter(file, true);
            String line = "0 " + command + "\n";
            writer.append(line);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes CharSequence to File using a FileWriter.
     *
     * @param file File to be written to.
     * @param chars CharSequence that holds the content to be written to the file.
     */
    private void writeCharSequenceToFile(File file, CharSequence chars) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.append(chars);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
