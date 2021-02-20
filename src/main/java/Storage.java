package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> load() throws LoadTasksException {
        ArrayList<String> taskCommands = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String taskCommand = br.readLine();
                while (taskCommand != null) {
                    taskCommands.add(taskCommand);
                    taskCommand = br.readLine();
                }
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new LoadTasksException();
        }
        return taskCommands;
    }

    public void writeTasksToDisk(TaskList tasks) throws WriteTasksException {
        try {
            ArrayList<String> writableTasks = tasks.getWritableTasks();
            FileWriter myWriter = new FileWriter(filePath);
            for (String writableTask : writableTasks) {
                myWriter.write(writableTask + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            throw new WriteTasksException();
        }
    }
}
