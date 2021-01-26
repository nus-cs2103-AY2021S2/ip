import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private String filePath;

    Database(String path) {
        filePath = path;
    }

    public void syncFromFile(ArrayList<Task> tasks) {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] keyWords = line.split(" \\| ");
                String taskType = keyWords[0].strip();
                String taskName;
                String taskStatus;
                Task thisTask;

                if (taskType.equals("T")) {
                    taskName = keyWords[2];
                    thisTask = new Todo(taskName);
                } else if (taskType.equals("D")) {
                    taskName = keyWords[2];
                    LocalDateTime cutOffDate = Parser.parseDateTime(keyWords[3]);
                    thisTask = new Deadline(taskName, cutOffDate);
                } else {
                    taskName = keyWords[2];
                    LocalDateTime startDate = Parser.parseDateTime(keyWords[3]);
                    thisTask = new Event(taskName, startDate);
                }
                taskStatus = keyWords[1].strip();
                if (taskStatus.equals("1")) {
                    thisTask.complete();
                }
                tasks.add(thisTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
        } catch (InvalidDateTimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateInFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Has no targeted file in: " + e.getMessage());
        }
    }
}
