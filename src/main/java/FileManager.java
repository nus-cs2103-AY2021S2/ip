import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

/**
 * Class that sets up the save/load system in project Duke.
 */

public class FileManager {

    static Path dir = Paths.get("data");
    static Path file = Paths.get("data/saveFile.txt");

    public FileManager() {
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
     * Function that saves the file after each usage of duke, saves when the user inputs the command
     * "bye".
     */
    public static void saveFile() {
        try {
            String save = "";
            for (int i = 0; i < Skeleton.storage.size(); i++) {
                save += Skeleton.storage.get(i) + "\n";
            }
            save = save.replaceAll("\u2713", "1");
            save = save.replaceAll("\u2718", "0");
            save = save.replaceAll("\\[", "" );
            save = save.replaceAll("]", "");
            save = save.replaceAll("\\(", "");
            save = save.replaceAll("\\)", "");
            Files.writeString(Paths.get("data/saveFile.txt"), save);
        } catch (IOException E) {
            System.out.println(E.getMessage() + "IO Exception.");
        }
    }

    /**
     * Function that loads the file from previous usages of the chatbot Duke.
     */
    public static void loadFile() {
        try {
            List<String> contents = Files.readAllLines(Paths.get("data/saveFile.txt"));
            Skeleton.storage.clear();
            for (int i = 0; i < contents.size(); i++) {
                char type = contents.get(i).charAt(0);
                char isDone = contents.get(i).charAt(1);
                boolean status = isDone == '1';
                String rest = contents.get(i).substring(2);
                rest = rest.replaceFirst("^\\s*"," ");
                switch (type) {
                    case 'T':
                        Skeleton.storage.add(new Todo(rest, status));
                        break;
                    case 'D':
                        String[] restD1 = rest.split("by:");
                        Skeleton.storage.add(new Deadline(restD1[0], status, restD1[1]));
                        break;
                    case 'E':
                        String[] restE1 = rest.split("at:");
                        Skeleton.storage.add(new Event(restE1[0], status, restE1[1]));
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException E) {
            System.out.println(E.getMessage() + "IO Exception.");
        }
    }
}
