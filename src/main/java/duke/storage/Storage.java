package duke.storage;

import duke.tasks.*;
import duke.taskList.TaskList;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * A Storage class that creates a storage object which
 * 1) Saves the Arraylist of Tasks into a .txt file.
 * 2) Loads the Arraylist of Tasks from a .txt file into a ArrayList Object
 */
public class Storage {
    public static String DIRECTORY;

    /**
     * Creates a storage object
     *
     * @param directory, the path of storage file
     */
    public Storage(String directory) {
        this.DIRECTORY = directory;
    }


    /** Saves the list of Tasks in the ArrayList object
     * into a .txt file.
     *
     *  @param taskList
     */
    public void saveTaskList(TaskList taskList) {
        Path path = Paths.get(this.DIRECTORY, "Data");
        try {
            /* Check if file path exists.
             * If it doesn't, create a new directory.
             */
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path filePath = Paths.get(this.DIRECTORY, "Data", "taskList.txt");
            File taskFile = filePath.toFile();

            /* Check if the file exists.
             * If it doesn't, create a new file
             */
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(taskFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Task task;
            for (int i = 0; i < taskList.getSize(); i++) {
                task = taskList.getTask(i);
                bufferedWriter.write(task.toSave());
                bufferedWriter.write("~");

                if(task.getDoneStatus()) {
                    bufferedWriter.write("1");
                } else {
                    bufferedWriter.write("0");
                }
                bufferedWriter.write("++");
            }
            bufferedWriter.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }


    /**
     * Loads the list of Tasks in the .txt file into the TaskList object
     *
     * @param taskList
     */
    public void loadTasks(TaskList taskList) {
        Path filePath = Paths.get(this.DIRECTORY, "Data", "taskList.txt");

        if (filePath.toFile().exists()) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(filePath);
                String data = bufferedReader.readLine();

                if (data != null) {
                    String[] tasks = data.split("\\+\\+");

                    for (int i = 0; i < tasks.length; i++) {
                        String[] doneList = tasks[i].split("~", 2);
                        String[] nameList = doneList[0].split(" ", 2);
                        String command = nameList[0];

                        createTask(command, doneList, nameList, taskList);
                    }
                }
            } catch (IOException error) {
                System.out.println(error.getMessage());
            }
        } else {
            File createFile = new File("/Data/duke.txt");
        }
    }

    /**
     * Creates the respective task based the command using details from nameList
     * and doneList before storing them into the taskList for loading
     *
     * @param command the command keyword to identify the task type
     * @param doneList a list of tasks stored
     * @param nameList list of details of each individual task
     * @param taskList the list to store the tasks allowing the user to modify the task objects
     */
    public void createTask(String command, String[] doneList, String[] nameList, TaskList taskList) {
        Task taskItem;
        switch (command) {
            case "DEADLINE":
                String[] deadlineItem = nameList[1].split("/by", 2);
                taskItem = new Deadline(deadlineItem[0],
                        deadlineItem[1],
                        checkDone(doneList[1]));
                taskList.addTask(taskItem);
                break;
            case "EVENT":
                String[] eventItem = nameList[1].split("/at", 2);
                taskItem = new Event(eventItem[0],
                        eventItem[1],
                        checkDone(doneList[1]));
                taskList.addTask(taskItem);
                break;
            case "TODO":
                taskItem = new ToDo(nameList[1], checkDone(doneList[1]));
                taskList.addTask(taskItem);
                break;
            default:
                break;
        }
    }

    /**
     * Checks if the task has been marked done before
     *
     * @param item, task to be checked
     * @return boolean indicating whether it has been marked done before
     */
    private static boolean checkDone (String item) {
        return item.equals("1");
    }
}
