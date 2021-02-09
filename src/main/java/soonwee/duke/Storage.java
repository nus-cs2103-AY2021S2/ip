package soonwee.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

/**
 * Represents a Storage instance. A storage instance will create new file,
 * update file and read its data.
 */
public class Storage {

    public String directory;
    public File fileObject;
    public TaskList taskList;
    static final int TASK_DESCRIPTION_INDEX = 7; //States the index in the string to read for description.
    static final int DATE_JUMP_INDEX = 5; //States the index jump from the previous read string.


    /**
     * Instantiates Storage with its target directory.
     */
    public Storage(String directory) {
        this.taskList = createNew();
        this.directory = directory;
        this.fileObject = createFile(directory);
    }

    public TaskList createNew() {
        return new TaskList();
    }

    /**
     * Creates a file in the specified directory.
     *
     * @param directory target directory
     * @return new file object
     */
    public File createFile(String directory) {
        try {
            File fileObject = new File(directory);
            if (fileObject.createNewFile()) {
                System.out.println("New file created: " + fileObject.getName());
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

    public void readFile() {
        try {
            File fileObject = new File("data\\tasks.txt");
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
     * @param data input data taken from command
     */
    public void processFileData(String data) {
        char taskType = data.charAt(1);
        String task;
        String time = new String();
        int secondSeg = data.indexOf("(");
        int endSeg = data.indexOf(")");
        task = data.substring(TASK_DESCRIPTION_INDEX);
        if (secondSeg != -1 && endSeg != -1) {
            task = data.substring(TASK_DESCRIPTION_INDEX, secondSeg);
            time = data.substring(secondSeg + DATE_JUMP_INDEX, endSeg);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        if (taskType == 'T') {
            this.taskList.addTask(new ToDo(task));
        } else {
            LocalDateTime formatDate = LocalDateTime.parse(time, formatter);
            if (taskType == 'D') {
                this.taskList.addTask(new Deadline(task, formatDate));
            } else if (taskType == 'E') {
                this.taskList.addTask(new Event(task, formatDate));
            }
        }
        if (data.charAt(4) == 'X') {
            taskList.getTask(taskList.tasksList.size() - 1).setCompleted();
        }
    }

    /**
     * Write tasks in TaskList into file.
     */
    public void writeFile() {
        try {
            FileWriter fileWriter = new FileWriter("data\\tasks.txt");
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