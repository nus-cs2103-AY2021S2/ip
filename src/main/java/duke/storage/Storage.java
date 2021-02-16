package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    private File file;

    /**
     * Stores test case inputs
     */
    public Storage() {
    }

    /**
     * Stores data of inputs.
     */
    public Storage(String pathName) {
        this.file = new File(Paths.get(pathName).toString());
    }

    /**
     * Saves data back in file in designated filePath.
     *
     * @param taskList TaskList of data to be saved
     * @throws IOException  If file is corrupt
     */
    public void saveData(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
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
    public TaskList loadData() throws IOException {
        Boolean doesFileExist = java.nio.file.Files.exists(Path.of(file.toString()));
        TaskList taskList = new TaskList();

        if (doesFileExist) {
            try {
                File info = new File(this.file.toString());
                Scanner sc = new Scanner(info);
                while (sc.hasNextLine()) {
                    String task = sc.nextLine();
                    String[] taskAsArray = task.split("");
                    String taskType = taskAsArray[1];
                    if (taskType.equals("T")) {
                        loadTodo(taskList, taskAsArray);
                    } else if (taskType.equals("D")) {
                        loadDeadline(taskList, taskAsArray);
                    } else if (taskType.equals("E")) {
                        loadEvent(taskList, taskAsArray);
                    }
                }
                sc.close();
            } catch (IOException e) {
                e.getMessage();
            }
        } else { //create new file in folder
            File folder = new File("data");
            File file = new File(this.file.toString());

            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        return taskList;

    }

    /**
     * Method to load todo tasks
     *
     * @param taskList is the task list to add the task to
     * @param taskAsArray input loaded from data.txt file
     */
    public void loadTodo(TaskList taskList, String[] taskAsArray) {
        Boolean isDone = false;
        if (taskAsArray[4].equals("X")) {
            isDone = true;
        }
        String taskDetails = "";
        for (int j = 7; j < taskAsArray.length; j++) {
            taskDetails += taskAsArray[j];
        }

        Task todoTask = new Todo(taskDetails);
        assert todoTask.isEmpty() : "Task should not be empty.";

        if (isDone) {
            todoTask.setDone();
        }
        taskList.addToDo(todoTask);
    }

    /**
     * Method to load deadline tasks
     *
     * @param taskList is the task list to add the task to
     * @param taskAsArray input loaded from data.txt file
     */
    public void loadDeadline(TaskList taskList, String[] taskAsArray) {
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
        for (int k = 0; k < taskDetailsAsArray.length - 1; k++) {
            taskDetails += taskDetailsAsArray[k];
        }

        String taskDateAndTime = taskDetailsDateAndTimeAsArray[1];
        String[] taskDateAndTimeAsArray = taskDateAndTime.split("by: ", 2);
        taskDateAndTime = taskDateAndTimeAsArray[1];
        String dateAndTime = "";
        taskDateAndTimeAsArray = taskDateAndTime.split("");
        for (int k = 0; k < taskDateAndTimeAsArray.length - 1; k++) {
            dateAndTime += taskDateAndTimeAsArray[k];
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
        Task deadlineTask = new Deadline(taskDetails, dateTime);
        assert deadlineTask.isEmpty() : "Task should not be empty.";

        if (isDone) {
            deadlineTask.setDone();
        }
        taskList.addDeadline(deadlineTask);
    }

    /**
     * Method to load event tasks
     *
     * @param taskList is the task list to add the task to
     * @param taskAsArray input loaded from data.txt file
     */
    public void loadEvent(TaskList taskList, String[] taskAsArray) {
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
        for (int k = 0; k < taskDetailsAsArray.length - 1; k++) {
            taskDetails += taskDetailsAsArray[k];
        }

        String taskDateAndTime = taskDetailsDateAndTimeAsArray[1];
        String[] taskDateAndTimeAsArray = taskDateAndTime.split("at: ", 2);
        taskDateAndTime = taskDateAndTimeAsArray[1];
        String dateAndTime = "";
        taskDateAndTimeAsArray = taskDateAndTime.split("");
        for (int k = 0; k < taskDateAndTimeAsArray.length - 1; k++) {
            dateAndTime += taskDateAndTimeAsArray[k];
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
        Task eventTask = new Event(taskDetails, dateTime);
        assert eventTask.isEmpty() : "Task should not be empty.";

        if (isDone) {
            eventTask.setDone();
        }
        taskList.addEvent(eventTask);
    }

}

