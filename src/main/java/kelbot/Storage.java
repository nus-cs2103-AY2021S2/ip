package kelbot;

import java.io.File;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class Storage {
    
    private java.nio.file.Path path;
    private boolean fileExists;
    
    /**
     * Initializes Storage
     *
     * @param path The file path that Kelbot will read from to load up previous usage
     */
    
    public Storage(java.nio.file.Path path) {
        this.path = path;
        fileExists = java.nio.file.Files.exists(path);
    }
    
    /**
     * Load data.txt file as Task List
     *
     * @return the task list to be loaded
     * @throws KelbotException if there is no existing Kelbot.txt, and a new empty Kelbot.txt is created instead
     */
    
    public TaskList load() throws KelbotException {
        if (fileExists) {
            try {
                FileInputStream fis = new FileInputStream(path.toString());
                ObjectInputStream ois = new ObjectInputStream(fis);
                TaskList taskList = new TaskList((ArrayList<Task>) ois.readObject());
                ois.close();
                System.out.println("Here is your task list from your previous Kelbot usage");
                System.out.println(taskList);
                return taskList;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            throw new KelbotException("No existing data file detected. A new file will be created");
        }
        return null;
    }
    
    /**
     * Save Task List as data.txt file
     *
     * @param taskList The task list to be saved
     */
    
    public void save(ArrayList<Task> taskList) {
        File file = new File(path.toString());
        file.getParentFile().mkdirs();
        try {
            FileOutputStream fos = new FileOutputStream(path.toString());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
