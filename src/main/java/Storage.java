import Exceptions.InvalidFolderException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Storage {
    public Storage(String filePath) throws InvalidFolderException {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();
            } else {
                readFileContents(filePath);
            }
        } catch (IOException ex) {
            throw new InvalidFolderException();
        }
    }

    //When Duke startup, read file content & input into a list.
    public List<Task> readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        List<Task> list = new ArrayList<>();

        Task task;
        String taskDescription;
        LocalDateTime taskDate;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        while (s.hasNextLine()) {
            String taskString = s.nextLine();
            String taskDone = taskString.substring(4, 5);
            int indexOfDivider = taskString.indexOf('|', 8);

            if (taskString.startsWith("T")) {
                taskDescription = taskString.substring(8);
                task = new ToDo(taskDescription);

            } else {
                taskDescription = taskString.substring(8, indexOfDivider);
                taskDate = LocalDateTime.parse(taskString.substring(indexOfDivider + 2), df);
                //taskDate = LocalDateTime.parse(taskString.split(" | ")[4]);

                if (taskString.startsWith("D")) {
                    task = new Deadline(taskDescription, taskDate);
                } else {
                    task = new Event(taskDescription, taskDate);
                }
            }

            if (taskDone.equals("1")) {
                task.markAsDone();
            }

            list.add(task);
        }

        return list;
    }

    public void overWriteFile(String filePath, List<Task> list) {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task : list) {
                boolean done = task.isDone;
                String doneString = "0";
                String taskDescription = task.description;
                String taskDate;

                if (done) {
                    doneString = "1";
                }

                if (task.taskType.equals("ToDo")) {
                    fw.write("T | " + doneString + " | " + taskDescription + "\n");
                } else {
                    taskDate = task.getTaskDate();

                    if (task.taskType.equals("Deadline")) {
                        fw.write("D | " + doneString + " | " + taskDescription + "| " + taskDate + "\n");
                    } else {
                        fw.write("E | " + doneString + " | " + taskDescription + "| " + taskDate + "\n");
                    }
                }
            }

            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
