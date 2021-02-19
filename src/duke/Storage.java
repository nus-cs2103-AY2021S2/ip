package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class with methods for saving and loading files.
 */
public class Storage {
    String path;
    String directory;

    Storage() {
    }

    Storage(String path, String directory) {
        this.path = path;
        this.directory = directory;
    }

    /**
     * Loads data from files if there is data to load.
     * @return A list of tasks according to the saved data.
     */
    ArrayList<Task> loadFile() {
        // String path = "./data/duke.txt";
        // String directory = "./data";
        File d = new File(directory);
        File f = new File(path);
        ArrayList<Task> list = new ArrayList<>();
        if (!d.exists()){
            d.mkdirs();
        }
        try {
            f.createNewFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                try {
                    String line = sc.nextLine();
                    Task task = Parser.parseFile(line);
                    list.add(task);
                } catch (Exception e) {}
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Saves individual tasks to the existing data file.
     * @param input String to be stored in the file.
     */
    void saveFile(String input) {
        // String path = "./data/duke.txt";
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(input + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error has occurred, unable to save.");
            e.getMessage();
        }
    }

    /**
     * Overwrites file with new list of task if changes are made to the list.
     * @param list New list of tasks to be saved.
     */
    void saveFile(ArrayList<Task> list) {
        // String path = "./data/duke.txt";
        try {
            FileWriter fw = new FileWriter(path);
            for(Task t: list) {
                fw.write(t.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
