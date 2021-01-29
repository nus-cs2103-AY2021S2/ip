package duke.storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Stores and Retrieves data from a given .txt file and loads it into TaskList.
 */
public class Storage {
    String str;

    /**
     * Stores filePath.
     *
     * @param filePath path where files are stored and retrieved
     */
    public Storage(String filePath) {
        this.str = filePath;
    }

    /**
     * Saves data back in file in designated filePath.
     *
     * @param li TaskList of data to be saved
     * @throws IOException  If file is corrupt
     */
    public static void saveData(TaskList li) throws IOException {
        //potential problem: saveData doesnt update .txt file
        //when i change done status of item to done. only updates after bye command
        try {
            Path currPath = Paths.get("");
            FileWriter fw = new FileWriter(currPath.toAbsolutePath().toString()
                    + "/src/main/java/duke/duke.txt");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        Path currPath = Paths.get("");
        FileWriter fw = new FileWriter(currPath.toAbsolutePath().toString()
                + "/src/main/java/duke/duke.txt");
        for (int i = 0; i < li.getSize(); i++) {
            String write = li.getInd(i) + "\n";
            fw.write(write);
        }
        fw.close();
    }

    /**
     * Loads data from designated file and converts it to a TaskList.
     *
     * @return list TaskList of all loaded data
     * @throws FileNotFoundException  If file cannot be found in path
     */
    public static TaskList loadData() throws FileNotFoundException {
        Path currPath = Paths.get("");
        Path dukePath = Paths.get(currPath.toAbsolutePath().toString()
                + "/src/main/java/duke/duke.txt");
        TaskList list = new TaskList();

        if (Files.exists(dukePath)) { //load list data
            File info = new File(String.valueOf(dukePath));
            Scanner sc = new Scanner(info);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] arr = task.split("");
                if (arr[1].equals("T")) {
                    Boolean done = false;
                    if (arr[4].equals("X")) {
                        done = true;
                    }
                    String taskDetails = "";
                    for (int j = 7; j < arr.length; j++) {
                        taskDetails += arr[j];
                    }

                    Task t = new Todo(taskDetails);
                    if (done) {
                        t.setDone();
                    }
                    list.addToDo(t);
                } else if (arr[1].equals("D")) {
                    String taskDetails = "";
                    Boolean done = false;
                    if (arr[4].equals("X")) {
                        done = true;
                    }
                    for (int j = 7; j < arr.length; j++) {
                        taskDetails += arr[j];
                    }
                    arr = taskDetails.split("[(]");
                    String[] deets = arr[0].split("");
                    String description = "";
                    for (int t = 0; t < arr[0].length() - 1; t++) {
                        description += deets[t];
                    }
                    taskDetails = arr[1];
                    arr = taskDetails.split(" by:", 2);
                    String by = "";
                    String details = arr[0];
                    arr = details.split("");
                    for (int k = 4; k < arr.length - 1; k++) {
                        by += arr[k];
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                    Task t = new Deadline(description, dateTime);
                    if (done) {
                        t.setDone();
                    }
                    list.addDeadline(t);
                } else if (arr[1].equals("E")) {
                    String taskDetails = "";
                    Boolean done = false;
                    if (arr[4].equals("X")) {
                        done = true;
                    }
                    for (int j = 7; j < arr.length; j++) {
                        taskDetails += arr[j];
                    }
                    arr = taskDetails.split("[(]");
                    String[] deets = arr[0].split("");
                    String description = "";
                    for (int t = 0; t < arr[0].length() - 1; t++) {
                        description += deets[t];
                    }
                    taskDetails = arr[1];
                    arr = taskDetails.split(" at: ");
                    String at = "";
                    String details = arr[0];
                    arr = details.split("");
                    for (int k = 4; k < arr.length - 1; k++) {
                        at += arr[k];
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(at, formatter);
                    Task t = new Event(description, dateTime);
                    if (done) {
                        t.setDone();
                    }
                    list.addEvent(t);
                }
            }
        } else { //create new file in folder
            File f = new File(currPath.toAbsolutePath().toString() + "/duke/duke.txt");
        }
        return list;
    }
}

