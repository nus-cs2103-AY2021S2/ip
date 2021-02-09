package duke;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final String fileName;

    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    /**
     * Writes the tasks in the task list into the specified file.
     *
     * @param tasks Task list to be saved.
     */
    public void saveTaskList(String tasks) {
        try {
            FileWriter fw = new FileWriter(filePath + fileName);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            System.out.println("This file cannot be saved... much like you!");
        }
    }

    /**
     * Reads the saved task list in the file and adds the task into the task list.
     *
     * @param taskList Task list to load the tasks from the file to.
     */
    public void loadTaskList(TaskList taskList) {
        try {
            File f = new File(filePath + fileName);
            f.createNewFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                String[] taskArr = taskString.split("\\| ");
                Task task = null;
                switch(taskArr[0]) {
                case "T ":
                    String input = taskArr[2];
                    task = new Todo(input);
                    break;
                case "D ":
                    LocalDateTime dateTime = LocalDateTime.parse(taskArr[3].substring(4),
                            DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"));
                    task = new Deadline(dateTime, taskArr[2].substring(0, taskArr[2].length() - 1));
                    break;
                case "E ":
                    LocalDateTime dateTimeStart = LocalDateTime.parse(taskArr[3].substring(6, taskArr[3].length() - 1),
                            DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"));
                    LocalDateTime dateTimeEnd = LocalDateTime.parse(taskArr[4].substring(4),
                            DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"));
                    task = new Event(dateTimeStart, dateTimeEnd, taskArr[2].substring(0, taskArr[2].length() - 1));
                    break;
                }
                if (task != null && taskArr[1].equals("X ")) {
                    task.checkTask();
                    taskList.addTask(task);
                }
            }
        } catch (IOException e) {
            System.out.println("This file cannot be loaded... My apologies");
        }
    }
}
