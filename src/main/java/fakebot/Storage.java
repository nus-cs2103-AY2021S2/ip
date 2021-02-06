package fakebot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fakebot.task.Task;
import fakebot.task.TaskList;


/**
 * Storage class use for storing and loading data from file.
 */
public class Storage {

    /**
     * File name and path to be edit.
     */
    private String fileToEdit;

    /**
     * Constructor of storage class.
     *
     * @param fileName name of file.
     * @param filePath path of file.
     */
    public Storage(String fileName, String filePath) {
        filePath = createDirectory(filePath);
        fileToEdit = filePath + fileName;
    }

    /**
     * Creates directory if file path doesn't exist.
     *
     * @param filePath path of file.
     * @return a path created, return empty path if path is invalid.
     */
    private String createDirectory(String filePath) {
        File file = new File(filePath);

        file.mkdirs();

        //Remove directory and use root instead since it invalid
        if (!file.isDirectory()) {
            return "";
        }
        return filePath;
    }

    /**
     * Writes tasks to file.
     *
     * @param taskList Tasks to write to file.
     */
    public void writeTasksToFile(TaskList taskList) {
        String textToAdd = Parser.convertStringListToString(Parser.convertTasksToStrings(taskList));
        try {
            writeToFile(textToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes string to file.
     *
     * @param textToAdd write text to file.
     */
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(fileToEdit);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Reads task from file.
     *
     * @return a list of task read from file.
     */
    public List<Task> tryReadTaskFile() {
        List<Task> taskList = new ArrayList<Task>();
        try {
            List<String> stringList = readFile();
            taskList = Parser.convertStringsToTasks(stringList);
        } catch (FileNotFoundException e) {
            //Do nothing, return empty list.
        }
        return taskList;
    }

    /**
     * Reads string from file.
     *
     * @return a list of string read from file.
     */
    private List<String> readFile() throws FileNotFoundException {
        List<String> stringList = new ArrayList<String>();

        File f = new File(fileToEdit);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            stringList.add(s.nextLine());
        }

        return stringList;
    }

}
