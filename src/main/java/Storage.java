import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage handles the loading of tasks from the file and making changes
 * to the file based on user commands.
 */
public class Storage {

    public static final int INVALID_INDEX = -1;
    public static final int INDEX_OFFSET = 1;
    public static final int START_OF_DESCRIPTION = 7;
    public static final int OFFSET_TO_DATE_TIME = 5;
    public static final int INDEX_OF_TYPE = 1;
    public static final int INDEX_OF_CHECK_FOR_DONE = 4;

    protected boolean isFileOriginallyPresent;
    protected File localFile;
    protected String path;

    /**
     * Creates a new instance of Storage for a user.
     *
     * @param path Path of file that is to be saved on the hard disk.
     */
    public Storage(String path) {
        this.path = path;
        this.localFile = new File(path);
        this.isFileOriginallyPresent = true;
    }

    /**
     * Checks if directory and file exists and if they do not exist, create them.
     */
    public void checkFileExistence() {
        File directory = new File("data");
        if (!directory.isDirectory()) {
            directory.mkdir();
        }
        if (!localFile.exists()) {
            try {
                this.isFileOriginallyPresent = false;
                localFile.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Adds a task to the file.
     *
     * @param task Task to be added.
     * @throws IOException On file error.
     */
    public void addTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.localFile, true);
        fw.write(task.toString() + "\n");
        fw.close();
    }

    public void helperForTaskCreationFromData(int hashIndex, Character doneIndicator, Task task, List<Task> list,
                                              String data) {
        if (hashIndex != INVALID_INDEX) {
            String tag = data.substring(hashIndex + INDEX_OFFSET);
            task.setTag(tag);
        }
        if (doneIndicator == 'X') {
            task.setDone();
        }
        list.add(task);
    }

    /**
     * Load tasks into a list if the file exists on the hard disk.
     * @return A list consisting of all the tasks if the file exists,
     * if not an empty list is returned.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<Task>();
        if (this.isFileOriginallyPresent) {
            Scanner contents = null;
            try {
                contents = new Scanner((this.localFile));
            } catch (FileNotFoundException e) {
                e.getMessage();
            }

            while (contents.hasNext()) {
                String data = contents.nextLine();

                Character type = data.charAt(INDEX_OF_TYPE);
                Character isDone = data.charAt(INDEX_OF_CHECK_FOR_DONE);
                int startingIndex = data.indexOf('(');
                int endingIndex = data.indexOf(')');
                int findHash = data.indexOf('#');
                Task task = null;

                switch (type) {
                case 'T':
                    String todoDescription = data.substring(START_OF_DESCRIPTION);
                    if (findHash != INVALID_INDEX) {
                        todoDescription = data.substring(START_OF_DESCRIPTION, findHash - INDEX_OFFSET);
                    }
                    task = new ToDo(todoDescription);
                    break;
                case 'D':
                    String deadlineDescription = data.substring(START_OF_DESCRIPTION, startingIndex - INDEX_OFFSET);
                    String by = data.substring(startingIndex + OFFSET_TO_DATE_TIME, endingIndex);
                    task = new Deadline(deadlineDescription, DateTimeHandler.convertDateTime(by));
                    break;
                case 'E':
                    String eventDescription = data.substring(START_OF_DESCRIPTION, startingIndex - INDEX_OFFSET);
                    String time = data.substring(startingIndex + OFFSET_TO_DATE_TIME, endingIndex);
                    task = new Event(eventDescription, DateTimeHandler.convertDateTime(time));
                    break;
                default:
                    break;
                }
                if (type == 'T' | type == 'D' | type == 'E') {
                    helperForTaskCreationFromData(findHash, isDone, task, tasks, data);
                }
            }
        }
        return tasks;
    }

    /**
     * Deletes a task from the file.
     *
     * @param task Task to be deleted.
     * @throws IOException On file error.
     */
    public void deleteTask(Task task) throws IOException {
        Scanner contents = new Scanner(this.localFile);
        File temp = new File("data/temp.txt");
        temp.createNewFile();
        FileWriter tempFile = new FileWriter(temp, true);

        while (contents.hasNext()) {
            String data = contents.nextLine();
            if (!data.equals(task.toString())) {
                tempFile.write(data + "\n");
            }
        }

        tempFile.close();
        copyFile(temp, localFile);
        temp.delete();
    }

    /**
     * Marks a task in the file as completed.
     *
     * @param task Task to be marked as completed.
     * @throws IOException On file error.
     */
    public void markTask(Task task) throws IOException {
        File temp = new File("data/temp.txt");
        temp.createNewFile();
        FileWriter tempFile = new FileWriter(temp, true);
        Scanner contents = new Scanner(this.localFile);

        while (contents.hasNext()) {
            String data = contents.nextLine();
            if (!data.equals(task.toString())) {
                tempFile.write(data + "\n");
            } else {
                task.setDone();
                tempFile.write(task.toString() + "\n");
            }
        }

        tempFile.close();
        copyFile(temp, localFile);
        temp.delete();
    }

    /**
     * Adds a tag to a task.
     *
     * @param task Task with the new tag to be added.
     * @throws IOException On file error.
     */
    public void addTag(Task task, String tag) throws IOException {
        File temp = new File("data/temp.txt");
        temp.createNewFile();
        FileWriter tempFile = new FileWriter(temp, true);
        Scanner contents = new Scanner(this.localFile);

        while (contents.hasNext()) {
            String data = contents.nextLine();
            if (data.equals(task.toString())) {
                tempFile.write(task.toString() + "  #" + tag + "\n");
                System.out.println(task.toString());
            } else {
                tempFile.write(data + "\n");
            }
        }

        tempFile.close();
        copyFile(temp, localFile);
        temp.delete();
    }

    /**
     * Copy the contents of one file into another.
     *
     * @param input File containing the contents to be copied.
     * @param output File where the contents are to be copied into.
     * @throws IOException On file error.
     */
    public void copyFile(File input, File output) throws IOException {
        FileInputStream in = new FileInputStream(input);
        FileOutputStream out = new FileOutputStream(output);
        byte[] data = new byte[1024];
        int len = in.read(data);

        while (len != -1) {
            out.write(data, 0, len);
            len = in.read(data);
        }

        in.close();
        out.close();
    }
}
