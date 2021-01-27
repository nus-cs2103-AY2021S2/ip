package duke.storage;

import duke.task.TaskList;
import duke.task.Task;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File saveFile;

    public Storage(String filePath) {
        saveFile = new File(filePath);
        String[] token = filePath.split("/");
        String directoryPath = "";
        for (int i = 0; i < token.length - 1; i++) {
            directoryPath += token[i] + "/";
        }
        File directory = new File(directoryPath);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            saveFile.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void save(TaskList tasks) {
        List<Task> list = tasks.getList();
        try {
            FileWriter fileWriter = new FileWriter(saveFile);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                fileWriter.write(task.toSaveFormat());
                fileWriter.write("\n");
                fileWriter.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public LinkedList<Task> load() throws DukeException{
        try {
            LinkedList<Task> list = new LinkedList<>();
            if (saveFile.exists()) {
                Scanner reader = new Scanner(saveFile);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] token = line.split(" \\| ");
                    Task task;
                    if (token[0].equals("T")) {
                        task = new Task(token[0], token[2]);
                    } else if (token[0].equals("E") || token[0].equals("D")) {
                        task = new Task(token[0], token[2], token[3], token[4]);
                    } else {
                        throw new DukeException("Save file is corrupted ): Will be creating a new file");
                    }
                    if (token[1].equals("1")) {
                        task.setDone();
                    }
                    list.add(task);
                }
            }
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new LinkedList<>();
    }
}
