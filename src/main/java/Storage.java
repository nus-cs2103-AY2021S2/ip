package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;
    private final Path dirPath;

    public Storage(Path filePath, Path dirPath) {
        this.filePath = filePath;
        this.dirPath = dirPath;
    }


    public void writeToFile(TaskList taskList) {
//        String home = System.getProperty("user.home");

//        Path dirPath = Paths.get(home, "data");
//        Path filePath = Paths.get(home,"data", "duke.txt");
        List<Task> myList = taskList.getTasks();

        try {
            boolean dirExists = Files.exists(dirPath);
            boolean fileExists = Files.exists(filePath);

            if (!dirExists) {
                Files.createDirectory(dirPath);
            }
            if (!fileExists) {
                Files.createFile(filePath);
            }

            BufferedWriter bfWriter = Files.newBufferedWriter(filePath);

            for (Task task : myList) {
                bfWriter.write(task.fileFormat() + "\n");
            }

            bfWriter.close();

        } catch(IOException err) {
            err.printStackTrace();
        }
    }


    public List<Task> readFromFile(){

        List<Task> tasksList = new ArrayList<>();
        final String DELIMITER = " \\| ";

        try {
            boolean dirExists = Files.exists(dirPath);
            boolean fileExists = Files.exists(filePath);

            if (!dirExists) {
                Files.createDirectory(dirPath);
            }
            if (!fileExists) {
                Files.createFile(filePath);
            }

            Task task = null;
            BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));
            String input = br.readLine();
            while (input != null) {
                String[] inputArr = input.split(DELIMITER);

                try {
                    switch (inputArr[0]) {
                        case "T":
                            task = new Todo(inputArr[2]);
                            break;
                        case "D":
                            task = new Deadline(inputArr[2], LocalDate.parse(inputArr[3]));
                            break;
                        case "E":
                            String[] timeParams = inputArr[3].split(" ", 2);
                            task = new Event(inputArr[2], LocalDate.parse(timeParams[0]), timeParams[1]);
                            break;
                    }

                    if (Integer.parseInt(inputArr[1]) == 1) {
                        tasksList.add(task.markAsDone());
                    } else {
                        tasksList.add(task);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                input = br.readLine();
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasksList;
    }
}
