import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;


public class DataFile {

    private static Path fileLocation = Paths.get("./data", "duke.txt");
    private static Path directoryLocation = Paths.get("./data");
    public static ArrayList<Task> data;

    private static void createFile() {
        try {
            Files.createFile(fileLocation);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static boolean checkForFile() {
        boolean fileExists = Files.exists(fileLocation);
        return fileExists;
    }

    private static boolean checkForDirectory() {
        boolean directoryExists = Files.exists(directoryLocation);
        return directoryExists;
    }



    private static void createDirectory() {
        try {
            Files.createDirectory(directoryLocation);
            System.out.println("directory created");
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public static ArrayList<Task> getDataFromFile() throws UnableToLoadDataException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.lines(fileLocation)
                    .map(task -> task.trim())
                    .filter(task -> !task.isEmpty())
                    .forEach(task -> {
                        String[] split = task.split("\\|");
                        String typeOfTask = split[0].trim();
                        String isDone = split[1].trim();
                        String name = split[2].trim();
                        String time = typeOfTask.equals("T") ? "" : split[3].trim();
                        if (typeOfTask.equals("T")) {
                            tasks.add(new Todo(name));
                            if (isDone.equals("1")) {
                                tasks.get(tasks.size() - 1).isDone = true;
                            }
                        }
                        if (typeOfTask.equals("D")) {
                            tasks.add(new Deadline(name, time));
                            if (isDone.equals("1")) {
                                tasks.get(tasks.size() - 1).isDone = true;
                            }
                        }
                        if (typeOfTask.equals("E")) {
                            tasks.add(new Event(name, time));
                            if (isDone.equals("1")) {
                                tasks.get(tasks.size() - 1).isDone = true;
                            }
                        }
                    });
            } catch (IOException e) {
                throw new UnableToLoadDataException();
            }
        return tasks;
    }

    public static void saveDataToFile(List<Task> tasks) throws UnableToSaveDataException{
        try {
            BufferedWriter writer = Files.newBufferedWriter(fileLocation, StandardCharsets.UTF_8);
            for (Task task: tasks) {
                writer.write(task.getSaveString());
                writer.write("\n");
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            throw new UnableToSaveDataException();
        }
    }

    public static ArrayList<Task> getData() {
        boolean directoryExists = checkForDirectory();
        if(!directoryExists) {
            createDirectory();
        }
        boolean fileExists = checkForFile();
        if(!fileExists) {
            createFile();
            data = new ArrayList<Task>();
        } else {
            try {
                data = getDataFromFile();
            } catch (UnableToLoadDataException er) {
                System.out.println("\nI'm sorry, I am not able to load data.\n");
            }
        }
        return data;
    }
}
