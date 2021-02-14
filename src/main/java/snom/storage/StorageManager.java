package snom.storage;

import snom.exceptions.SnomException;
import snom.model.task.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages storage related process for snom.model.Snom
 * Eg. Import task, save task
 *
 * @author Sharptail
 */

public class StorageManager extends FileManager{
    public StorageManager(String filename){
        super(filename);
        super.createFolder();
        super.createFile();
    }

    /**
     * Returns an array list of task from given list of strings
     *
     * @return               array list of snom.tasks
     * @throws SnomException if invalid date for deadline or event
     */
    public ArrayList<Task> importTask() throws SnomException{
        List<String> lines = super.readFile();
        ArrayList<Task> taskList = new ArrayList<>();
        for(String line: lines) {
            String attr[] = line.split(",");
            switch (attr[0]) {
            case "T":
                Todo todo = new Todo(attr[2]);
                todo.setStatus(Boolean.parseBoolean(attr[1]));
                taskList.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(attr[2], attr[3]);
                deadline.setStatus(Boolean.parseBoolean(attr[1]));
                taskList.add(deadline);
                break;
            case "E":
                Event event = new Event(attr[2], attr[3]);
                event.setStatus(Boolean.parseBoolean(attr[1]));
                taskList.add(event);
                break;
            default:
            }
        }
        return taskList;
    }

    /**
     * Writes the given task list into the save file
     *
     * @param taskList list of snom.tasks
     */
    public void saveFile(TaskList taskList){
        try {
            Files.writeString(path, "");
            for(Task task: taskList.getList()){
                Files.writeString(path, task.toSaveString() + "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
