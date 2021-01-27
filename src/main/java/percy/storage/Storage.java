package percy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;


import percy.exception.PercyException;
import percy.task.Task;
import percy.task.TaskList;
import percy.task.Deadline;
import percy.task.Todo;
import percy.task.Event;

public class Storage {
    private static final String DELIMITER = " \\| ";
    private File file;
    private String filePath;

    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);

        // assert file != null;
        if(!this.file.exists()) { // If file does not exist, folder does not exist
            file.getParentFile().mkdir(); // Creates data folder
            file.createNewFile(); // throws IOException  create a file in abstract directory
        }
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        int taskCount = 0;
        Scanner scanner = new Scanner(this.file);
        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            String[] splitData = data.split(DELIMITER);
            String taskType = splitData[0].trim();
            String status = splitData[1].trim();
            String description = splitData[2].trim();

            switch (taskType) {
            case Todo.PREFIX:
                taskList.add(new Todo(description));
                taskCount++;
                if (status.equals("1")) {
                    taskList.get(taskCount - 1).doTask();
                }
                break;
            case Deadline.PREFIX:
                String[] dateTime = splitData[3].split(" ", 2);
                LocalDate date = LocalDate.parse(dateTime[0]);
                LocalTime time = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm"));
                taskList.add(new Deadline(description, date, time));
                taskCount++;
                if (status.equals("1")) {
                    taskList.get(taskCount - 1).doTask();
                }
                break;
            case Event.PREFIX:
                dateTime = splitData[3].split(" ", 2);
                date = LocalDate.parse(dateTime[0]);
                time = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm"));
                taskList.add(new Event(description, date, time));
                taskCount++;
                if (status.equals("1")) {
                    taskList.get(taskCount - 1).doTask();
                }
                break;
            default:
                System.out.println("Not found"); // change it to an error
            }
        }
        scanner.close();
        return taskList;
    }

    public void save(TaskList taskList) throws IOException { // catch?
        ArrayList<Task> taskArr = taskList.getTaskList();
        FileWriter file = new FileWriter(filePath);
        for (Task t: taskArr) {
            file.write(t.formatToFile() + "\n");
        }
        file.close();
    }

    /*
        private String[] parseDate() {
     */

    }
