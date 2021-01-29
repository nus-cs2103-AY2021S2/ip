package soonwee.duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Storage instance. A storage instance will create new file,
 * update file and read its data.
 */
public class Storage {
    public String directory;
    public File fileObject;
    public FileWriter fileWriter;
    public TaskList taskList;

    /**
     * Instantiate Storage with its target directory.
     */
    public Storage(String directory) {
        this.taskList = createNew();
        this.directory = directory;
        this.fileObject = createFile(directory);
    }

    /**
     * Create new TaskList instance for this instance.
     *
     * @return a TaskList instance
     */
    public TaskList createNew() {
        return new TaskList();
    }

    /**
     * Create a file in the specified directory.
     *
     * @param directory target directory.
     * @return new file object
     */
    public File createFile(String directory) {
        try {
            File fileObject = new File(directory);
            if (fileObject.createNewFile()) {
                System.out.println("New file created: "
                    + fileObject.getName());
            } else {
                System.out.println("The file exists. Reading file...");
                readFile();
            }
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
        return fileObject;
    }

    /**
     * Read file contents.
     */
    public void readFile() {
        try {
            File fileObject = new File("C:\\Users\\songs\\Desktop\\CS2103\\data\\tasks.txt");
            Scanner reader = new Scanner(fileObject);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                processFileData(data);
            }
            System.out.println("Reading done.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    /**
     * Process data in file and add them into memory task list.
     *
     * @param data input data taken from command.
     */
    public void processFileData(String data) {
        char taskType = data.charAt(1);
        String task = new String();
        String time = new String();
        int secondSeg = data.indexOf("(");
        int endSeg = data.indexOf(")");
        task = data.substring(7);
        if(secondSeg != -1 && endSeg != -1) {
            task = data.substring(7, secondSeg);
            time = data.substring(secondSeg + 5, endSeg);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        if (taskType == 'T') {
            this.taskList.addTask(new ToDo(task));
        } else {
            LocalDateTime formatDate = LocalDateTime.parse(time, formatter);
            if (taskType == 'D') {
                this.taskList.addTask(new Deadline(task, formatDate));
            } else {
                this.taskList.addTask(new Event(task, formatDate));
            }
        }
        if(data.charAt(4) == 'X') {
            taskList.getTask(taskList.tasksList.size() - 1).setCompleted();
        }
    }

    /**
     * Write tasks in TaskList into file.
     */
    public void writeFile() {
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\songs\\Desktop\\CS2103\\data\\tasks.txt");
            for (int i = 0; i < this.taskList.getSize(); i++) {
                fileWriter.write(this.taskList.getTask(i).toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An IOException has occurred.");
            e.printStackTrace();
        }
    }
}
