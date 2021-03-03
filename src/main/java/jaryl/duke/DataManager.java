package jaryl.duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * DataManager handles reading/writing data from/to local disk
 */
public class DataManager {
    private String filePath;
    private static final String DELIMITER = " \\| ";

    /**
     * Constructor to instantiate a new DataManager
     * @param filePath path to file
     */
    public DataManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Handles the creation and writing of data to file on local disk storage
     * @param tasksList list of tasks
     */
    public void writeToFile(ArrayList<Task> tasksList) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tasksList.size(); i++) {
            sb.append(tasksList.get(i).fileFormat()).append("\n");
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath));
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the reading of data from existing file on local disk storage
     */
    public ArrayList<Task> readFromFile() throws DukeException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        ArrayList<Task> tasksList = new ArrayList<>();
        LocalDateTime date = null;

        Path path = Paths.get(filePath);
        if(!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new InvalidFileException();
        }

        try {
            Task task = null;
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            String input = br.readLine();
            while(input != null) {
                String[] inputArr = input.split(DELIMITER);
                if(inputArr.length == 4) {
                    date = LocalDateTime.parse(inputArr[3], DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
                }

                try {
                    switch (inputArr[0]) {
                        case "T":
                            task = new Todo(inputArr[2]);
                            break;
                        case "D":
                            task = new Deadline(inputArr[2], df.format(date));
                            break;
                        case "E":
                            task = new Event(inputArr[2], df.format(date));
                            break;
                    }
                    if(Integer.parseInt(inputArr[1]) == 1) {
                        task.toggleStatus();
                    }
                    tasksList.add(task);
                } catch (InvalidFormatException e) {
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
