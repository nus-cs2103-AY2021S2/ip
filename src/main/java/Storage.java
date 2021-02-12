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

    protected String path;
    protected File localFile;
    protected boolean isFileOriginallyPresent;
    protected DateValidation validate;


    /**
     * Creates a new instance of Storage for a user.
     * @param path Path of file that is to be saved on the hard disk.
     */
    public Storage(String path) {
        this.path = path;
        this.localFile = new File(path);
        this.isFileOriginallyPresent = true;
        this.validate = new DateValidation();
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
                Character type = data.charAt(1);
                Character isDone = data.charAt(4);
                int startingIndex = data.indexOf('(');
                int endingIndex = data.indexOf(')');
                int findHash = data.indexOf('#');
                switch (type) {
                case 'T':
                    String todoDescription = data.substring(7);
                    String todoTag = "";
                    if (findHash != INVALID_INDEX) {
                        todoDescription = data.substring(7, findHash - 1);
                        todoTag = data.substring(findHash + 1);
                    }
                    ToDo todo = new ToDo(todoDescription);
                    if (findHash != INVALID_INDEX) {
                        todo.setTag(todoTag);
                    }
                    if (isDone == 'X') {
                        todo.setDone();
                    }
                    tasks.add(todo);
                    break;
                case 'D':
                    String deadlineDescription = data.substring(7, startingIndex - 1);
                    String by = data.substring(startingIndex + 5, endingIndex);
                    Deadline deadline = new Deadline(deadlineDescription, validate.convertDate(by));
                    if (findHash != INVALID_INDEX) {
                        String deadlineTag = data.substring(findHash + 1);
                        deadline.setTag(deadlineTag);
                    }
                    if (isDone == 'X') {
                        deadline.setDone();
                    }
                    tasks.add(deadline);
                    break;
                case 'E':
                    String eventDescription = data.substring(9, startingIndex - 1);
                    String time = data.substring(startingIndex + 5, endingIndex);
                    Event event = new Event(eventDescription, time);
                    if (findHash != INVALID_INDEX) {
                        String eventTag = data.substring(findHash + 1);
                        event.setTag(eventTag);
                    }
                    if (isDone == 'X') {
                        event.setDone();
                    }
                    tasks.add(event);
                    break;
                default:
                    break;
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
     * @param task Task with the new tag to be added.
     * @throws IOException On file error.
     */
    public void addTag(Task task) throws IOException {
        File temp = new File("data/temp.txt");
        temp.createNewFile();
        FileWriter tempFile = new FileWriter(temp, true);
        Scanner contents = new Scanner(this.localFile);

        while (contents.hasNext()) {
            String data = contents.nextLine();
            if (!data.equals(task.toString())) {
                tempFile.write(data + "\n");
            } else {
                tempFile.write(task.toString() + " " + task.getTag() + "\n");
                System.out.println(task.toString());
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
