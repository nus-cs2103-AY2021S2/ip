package todobeast;

import todobeast.tasks.Deadline;
import todobeast.tasks.Event;
import todobeast.tasks.Task;
import todobeast.tasks.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {

    File data;
    public static final String STORAGE_DELIMITER = ".";
    public static final String STORAGE_DELIMITER_REGEX = "[.]";

    public Storage(String directoryPath, String fileName) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        data = new File(directoryPath + fileName);
        try {
            data.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not create new file, exiting process now.");
            System.exit(1);
        }
    }

    public void saveToStorage(String message) throws IOException {

        FileWriter fileWriter = new FileWriter(data);
        fileWriter.write(message);
        fileWriter.close();
    }

    public List<Task> loadTasks() throws FileNotFoundException {

        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(data);

        while (sc.hasNextLine()) {
            Task newTask = null;
            String line = sc.nextLine();
            // debug
//            System.out.println(line);
            String[] taskArgs = line.split(STORAGE_DELIMITER_REGEX);

            //debug
//            System.out.println(Arrays.toString(taskArgs));
            boolean isTaskDone = taskArgs[1].equals("1");



            switch (taskArgs[0]) {
            // format: TODO.1.desc
            case "TODO":
                newTask = new Todo(taskArgs[2], isTaskDone);
                break;
            // format: DEADLINE.1.desc.date.time
            case "DEADLINE":
                newTask = new Deadline(taskArgs[2], isTaskDone, LocalDate.parse(taskArgs[3]), LocalTime.parse(taskArgs[4]));
                break;
            // format: EVENT.1.desc.date.time
            case "EVENT":
                newTask = new Event(taskArgs[2], isTaskDone, LocalDate.parse(taskArgs[3]), LocalTime.parse(taskArgs[4]));
                break;
            }
            taskList.add(newTask);
        }
        sc.close();
        return taskList;
    }
}
