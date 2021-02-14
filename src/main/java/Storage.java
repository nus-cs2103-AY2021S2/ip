import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
/**
 * Class that sets up the save/load system in project Duke.
 */

public class Storage {

    static Path dir = Paths.get("data");
    static Path file = Paths.get("data/saveFile.txt");

    public Storage() {
        if (!Files.isDirectory(dir)) {
            try {
                Files.createDirectory(dir);
                if (!Files.exists(file)) {
                    Files.createFile(file);
                }
            } catch (IOException E) {
                System.out.println(E.getMessage() + " IO Exception detected.");
            }
        }
    }

    /**
     * Saves the tasklist in /data/saveFile.txt
     *
     * @param store the current tasklist of the user
     */
    public void saveFile(TaskList store) {
        ArrayList<Task> storage = store.getStorage();
        try {
            String save = "";
            for (int i = 0; i < storage.size(); i++) {
                save += storage.get(i).getData() + "\n";
            }
            save = reformatString(save);
            Files.writeString(Paths.get("data/saveFile.txt"), save);
        } catch (IOException E) {
            System.out.println(E.getMessage() + "IO Exception.");
        }
    }

    /**
     * Function that loads the file from previous usages of the chatbot Duke.
     */
    public void loadFile(TaskList store) {
        ArrayList<Task> storage = store.getStorage();
        try {
            List<String> contents = Files.readAllLines(Paths.get("data/saveFile.txt"));
            storage.clear();
            for (int i = 0; i < contents.size(); i++) {
                char type = contents.get(i).charAt(0);
                char status = contents.get(i).charAt(1);
                boolean isDone = status == '1';
                String rest = contents.get(i).substring(2);
                rest = rest.replaceFirst("^\\s*", " ");
                switch (type) {
                case 'T':
                    storage.add(new ToDo(rest, isDone));
                    break;
                case 'D':
                    String[] restD1 = rest.split("by:");
                    storage.add(new Deadline(restD1[0], isDone, restD1[1]));
                    break;
                case 'E':
                    String[] restE1 = rest.split("at:");
                    storage.add(new Event(restE1[0], isDone, restE1[1]));
                    break;
                default:
                    break;
                }
            }
        } catch (IOException E) {
            System.out.println(E.getMessage() + "IO Exception.");
        }
    }

    public String reformatString(String string) {
        String save = string;
        save = save.replaceAll("\u2713", "1");
        save = save.replaceAll("\u2718", "0");
        save = save.replaceAll("\\[", "");
        save = save.replaceAll("]", "");
        save = save.replaceAll("\\(", "");
        save = save.replaceAll("\\)", "");
        return save;
    }
}
