package duke;

import duke.handler.Parser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String STORAGE_PATH;
    private static final int MARK_INDEX = 2;

    public Storage(String storagePath) {
        STORAGE_PATH = storagePath;
    }

    public TaskList load() {
        TaskList tasks = new TaskList();
        Scanner dataReader = null;
        try{
            File file = new File(STORAGE_PATH);
            file.getParentFile().mkdirs();
            file.createNewFile();

            dataReader = new Scanner(file);
            while (dataReader.hasNextLine()) {
                Task task = Parser.parseFromData(dataReader.nextLine());
                tasks.addTask(task);
            }
            return tasks;
        } catch (IOException e) {
            System.out.println("File did not load");
        } finally {
            dataReader.close();
        }
        return tasks;
    }

    public void addTask(Task task){
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH, true);
            String toWrite ="";
            switch(task.getType()) {
            case "TODO":
                toWrite = String.format("%c|%c|%s",
                        'T',
                        task.isDone() ? 'X' : ' ',
                        task.getDescription());
                break;
            case "DEADLINE":
                toWrite = String.format("%c|%c|%s|%s",
                        'D',
                        task.isDone() ? 'X' : ' ',
                        task.getDescription(),
                        ((Deadline) task).getBy());
                break;
            case "EVENT":
                toWrite = String.format("%c|%c|%s|%s",
                        'E',
                        task.isDone() ? 'X' : ' ',
                        task.getDescription(),
                        ((Event) task).getAt());
                break;
            }
            fw.write(toWrite + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File could not be written to. Could not add task.");
        }
    }

    public void markDoneInStorage(Task task, int taskNum) {
        try {
            Path path = Paths.get(STORAGE_PATH);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String line = lines.get(taskNum - 1);
            StringBuilder updatedLine = new StringBuilder(line);
            updatedLine.setCharAt(MARK_INDEX, 'X');
            lines.set(taskNum - 1, updatedLine.toString());
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Fail to mark done in storage file.");
        }
    }

    public void delete(int taskNum) {
        try {
            Path path = Paths.get(STORAGE_PATH);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines.remove(taskNum - 1);
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Fail to delete item in storage file.");
        }
    }
}
