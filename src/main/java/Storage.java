import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    Storage(String path) {
        filePath = path;
    }

    public ArrayList<Task> syncFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

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
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        }
    }

    public void updateInFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Has no targeted file in: " + e.getMessage());
        }
    }
}
