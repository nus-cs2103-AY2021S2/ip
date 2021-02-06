package duke.storage;

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

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * Stores and Retrieves data from a given .txt file and loads it into TaskList.
 */
public class Storage {
    private String filePath;

    /**
     * Stores filePath.
     */
    public Storage() {
        this.filePath = System.getProperty("user.dir") + "/data/duke.txt";
    }

    /**
     * Saves data back in file in designated filePath.
     *
     * @param taskList TaskList of data to be saved
     * @throws IOException  If file is corrupt
     */
    public static void saveData(TaskList taskList) {
        try {
            Path currPath = Paths.get("");
            FileWriter fileWriter = new FileWriter(currPath.toAbsolutePath().toString()
                    + "/src/main/java/duke/duke.txt");
            for (int i = 0; i < taskList.getSize(); i++) {
                String write = taskList.getInd(i) + "\n";
                fileWriter.write(write);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads data from designated file and converts it to a TaskList.
     *
     * @return list TaskList of all loaded data
     * @throws FileNotFoundException  If file cannot be found in path
     */
    public static TaskList loadData() throws IOException {
        Path currPath = Paths.get("");
        Path dukePath = Paths.get(currPath.toAbsolutePath().toString()
                + "/src/main/java/duke/duke.txt");
        TaskList list = new TaskList();

        if (Files.exists(dukePath)) { //load list data
            File info = new File(String.valueOf(dukePath));
            Scanner sc = new Scanner(info);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskAsArray = task.split("");
                if (taskAsArray[1].equals("T")) {
                    Boolean isDone = false;
                    if (taskAsArray[4].equals("X")) {
                        isDone = true;
                    }
                    String taskDetails = "";
                    for (int j = 7; j < taskAsArray.length; j++) {
                        taskDetails += taskAsArray[j];
                    }

                    Task todoTask = new Todo(taskDetails);
                    if (isDone) {
                        todoTask.setDone();
                    }
                    list.addToDo(todoTask);
                } else if (taskAsArray[1].equals("D")) {
                    Boolean isDone = false;
                    if (taskAsArray[4].equals("X")) {
                        isDone = true;
                    }
                    String taskDetailsDateAndTime = "";
                    for (int j = 7; j < taskAsArray.length; j++) {
                        taskDetailsDateAndTime += taskAsArray[j];
                    }
                    String[] taskDetailsDateAndTimeAsArray = taskDetailsDateAndTime.split("[(]");
                    String[] taskDetailsAsArray = taskDetailsDateAndTimeAsArray[0].split("");
                    String taskDetails = "";
                    for (int t = 0; t < taskDetailsAsArray[0].length() - 1; t++) {
                        taskDetails += taskDetailsAsArray[t];
                    }

                    taskDetails = taskDetailsDateAndTimeAsArray[1];
                    String[] taskDateAndTimeAsArray = taskDetails.split(" by:", 2);
                    String taskDateAndTime = "";
                    String dateAndTime = taskDateAndTimeAsArray[0];
                    taskDateAndTimeAsArray = dateAndTime.split("");
                    for (int k = 4; k < taskDateAndTimeAsArray.length - 1; k++) {
                        taskDateAndTime += taskDateAndTimeAsArray[k];
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(taskDateAndTime, formatter);
                    Task deadlineTask = new Deadline(taskDetails, dateTime);

                    if (isDone) {
                        deadlineTask.setDone();
                    }
                    list.addDeadline(deadlineTask);
                } else if (taskAsArray[1].equals("E")) {
                    Boolean isDone = false;
                    if (taskAsArray[4].equals("X")) {
                        isDone = true;
                    }
                    String taskDetailsDateAndTime = "";
                    for (int j = 7; j < taskAsArray.length; j++) {
                        taskDetailsDateAndTime += taskAsArray[j];
                    }
                    String[] taskDetailsDateAndTimeAsArray = taskDetailsDateAndTime.split("[(]");
                    String[] taskDetailsAsArray = taskDetailsDateAndTimeAsArray[0].split("");
                    String taskDetails = "";
                    for (int t = 0; t < taskDetailsAsArray[0].length() - 1; t++) {
                        taskDetails += taskDetailsAsArray[t];
                    }

                    taskDetails = taskDetailsDateAndTimeAsArray[1];
                    String[] taskDateAndTimeAsArray = taskDetails.split(" at:", 2);
                    String taskDateAndTime = "";
                    String dateAndTime = taskDateAndTimeAsArray[0];
                    taskDateAndTimeAsArray = dateAndTime.split("");
                    for (int k = 4; k < taskDateAndTimeAsArray.length - 1; k++) {
                        taskDateAndTime += taskDateAndTimeAsArray[k];
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(taskDateAndTime, formatter);
                    Task eventTask = new Event(taskDetails, dateTime);

                    if (isDone) {
                        eventTask.setDone();
                    }
                }
            }
        } else { //create new file in folder
            File f = new File(currPath.toAbsolutePath().toString() + "/duke/duke.txt");
        }
        return list;
    }
}

