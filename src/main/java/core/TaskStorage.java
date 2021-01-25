package core;

import core.task.Task;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public interface TaskStorage {
    void saveTaskList(ArrayList<Task> tm, Path fileLocation) throws  IOException;
    ArrayList<Task> retrieveTaskList(Path fileLocation) throws IOException;
}
