package main.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() {
        try {
            String taskFileContent = fileHandler();
            return parseTaskFileContent(taskFileContent);
        } catch (FileNotFoundException ex) {
            // create new file for task data
            try {
                createFile();
                return parseTaskFileContent("");
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        } catch (ArrayIndexOutOfBoundsException arrayEx) {
            // nothing to catch, empty file
            arrayEx.printStackTrace();
        }
        return null;
    }

    public void writeToFile(TaskList tasks) {
        try {
            String content = parseTasksToString(tasks.get());
            File file = new File(this.filePath);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private String fileHandler() throws FileNotFoundException {
        // example file: T,1,read book|D,0,return book,June 6th|
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        String fileContent = "";
        while (s.hasNext()) {
            fileContent += s.nextLine();
        }
        return fileContent;
    }

    private void createFile() throws IOException {
        File f = new File(this.filePath);
        Files.createDirectories(Paths.get(this.filePath).getParent());
        Boolean success = f.createNewFile();
    }

    private List<Task> parseTaskFileContent(String fileContent) {
        // convert to tasks array

        if (fileContent.isEmpty()) {
            return new ArrayList<Task>();
        }

        List<Task> tempTask = new ArrayList<Task>();
        String[] tasks = fileContent.split("\\|");
        for (String task: tasks) {
            String[] taskInfo = task.split(",");

            String taskType = taskInfo[0];
            Boolean taskStatus = taskInfo[1].equals("1");

            String taskName = taskInfo[2];

            Task newTask = new Task(taskName);
            switch (taskType) {
                case "T":
                    newTask = new Todo(taskName, taskStatus);
                    break;
                case "E":
                    newTask = new Event(
                            taskInfo[2],
                            LocalDate.parse(taskInfo[3], DateTimeFormatter.ofPattern("MMM dd yyyy")),
                            taskStatus);
                    break;
                case "D":
                    newTask = new Deadline(
                            taskInfo[2],
                            LocalDate.parse(taskInfo[3], DateTimeFormatter.ofPattern("MMM dd yyyy")),
                            taskStatus);
                    break;
            }
            tempTask.add(newTask);
        }

        return tempTask;
    }

    private String parseTasksToString(List<Task> tasks) {
        String content = "";
        for (Task task : tasks) {
            content += task.toFileString() + "|";
        }
        return content;
    }
}
