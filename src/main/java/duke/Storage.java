package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles read and write operations for Task objects in Duke.
 */
public class Storage {
    private final Path filePath;
    private final Path dirPath;

    /**
     * Creates a Storage object
     *
     * @param filePath relative file path to where the data is stored
     * @param dirPath relative directory path
     */
    public Storage(Path filePath, Path dirPath) {
        this.filePath = filePath;
        this.dirPath = dirPath;
    }

    /**
     * Writes data (Task objects) to the file
     *
     * @param taskList the current list of tasks
     */
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

    /**
     * Reads data (Task objects) from the file
     *
     * @return the list of Task objects stored in the file
     */
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
