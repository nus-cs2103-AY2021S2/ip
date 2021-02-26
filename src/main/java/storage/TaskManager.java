package storage;

import data.task.ITask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class TaskManager {
    private final Path filePath;

    public TaskManager(Path filePath){
        this.filePath = filePath;
    }

    public static TaskManager getTaskManager(String filePath) {
        TaskManager tm = new TaskManager(Path.of(filePath));
        try {
            tm.createPath();
            return tm;
        } catch (IOException e) {
            System.out.println("Error creating file structure!");
        }
        System.exit(1);
        return null;
    }

    private void writeFile(String fileContent) throws IOException {
        FileWriter fileWriter = new FileWriter(String.valueOf(this.filePath));
        fileWriter.write(fileContent);
        fileWriter.close();
    }

    private List<String> readFile() throws FileNotFoundException {
        File f = new File(String.valueOf(filePath));
        List<String> taskStringList = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            taskStringList.add(s.nextLine());
        }
        return taskStringList;
    }

    public List<ITask> read() {
        try {
            return TaskDecoder.decodeTask(readFile());
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file!");
            e.printStackTrace();
        }
        throw new RuntimeException("Unable to read task list from disk, exiting...");
    }

    public void save(List<? extends ITask> list) {
        StringBuilder sb = new StringBuilder();

        list.forEach(x -> sb.append(x).append("\n"));
        try {
            writeFile(sb.toString());
        } catch (IOException e) {
            System.out.println("Unable to save the task!");
            e.printStackTrace();
        }

    }

    public void createPath() throws IOException{
            if(!Files.exists(filePath.getParent())){
                Files.createDirectory(filePath.getParent()) ;
                Files.createFile(filePath);
            } else if (!Files.exists(filePath)){
                Files.createFile(filePath);
            }
    }



}
