package core;

import core.task.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Implementation of a {@code TaskStorage} in a file location.
 */
public class TaskStorageFile extends TaskStorage {

    @Override
    public void saveTaskList(ArrayList<Task> tm, Path fileLocation) throws IOException {
        Files.createDirectories(fileLocation.getParent());
        try (var fWriter = new FileOutputStream(fileLocation.toFile()); var oos = new ObjectOutputStream((fWriter))) {
            oos.writeObject(tm);
        }
    }

    @Override
    public ArrayList<Task> retrieveTaskList(Path fileLocation) throws IOException {
        if (Files.isRegularFile(fileLocation)) {
            try (var fWriter = new FileInputStream(fileLocation.toFile()); var ois = new ObjectInputStream((fWriter))) {
                try {
                    var x = ois.readObject();
                    if(x instanceof ArrayList<?>) {
                        return (ArrayList<Task>) x;
                    }else {
                        return null;
                    }
                } catch (ClassNotFoundException e) {
                    return null;
                }
            }
        } else {
            return new ArrayList<>();
        }
    }
}
