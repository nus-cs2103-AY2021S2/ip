package duke.storage;

import duke.tasks.Task;
import duke.exceptions.DukeCorruptedStorageException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final static String DATA_DIR = new File("data").getAbsolutePath();
    private File saveFile = new File(DATA_DIR + "/save.txt");
    private static Storage INSTANCE = null;

    private Storage() {
        createDirectory();
    }

    public static Storage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Storage();
        }
        return INSTANCE;
    }

    public void update(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(saveFile);
            fw.write(StorageEncoder.encodeTasks(tasks));
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to write into save file." + e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!saveFile.createNewFile()) {
                Scanner sc = new Scanner(saveFile);
                ArrayList<String> inputs = new ArrayList<>();
                while (sc.hasNext()) {
                    inputs.add(sc.nextLine());
                }
                tasks = StorageDecoder.decodeSave(inputs);
            }
        } catch (IOException e) {
            System.out.println("Failed to create new file" + e.getMessage()) ;
        } catch (DukeCorruptedStorageException e) {
            System.out.println(e);
        }
        return tasks;
    }

    private void createDirectory() {
        Path dataPath = Paths.get(DATA_DIR);
        try {
            Files.createDirectories(dataPath);
        } catch (IOException e) {
            System.out.println("Failed to create new directory." + e.getMessage());
        }
    }
}
