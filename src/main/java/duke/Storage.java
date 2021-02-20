package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {

    static String filePath;
    static Path path;

    public Storage(String filePath, Path path) {
        this.filePath = filePath;
        this.path = path;
    }

    static void checkPath(String filePath, Path path) {
        try {
            File file = new File(filePath);
            Files.createDirectories(Path.of(file.getParent()));
            Files.createFile(path);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static void save(ArrayList<Command> xs, String path) {
        try {
            FileWriter fw = new FileWriter(path);
            for (Command c : xs) {
                if (c instanceof ToDo) {
                    fw.write("T |"
                            + c.getDone()
                            + "| "
                            + c.getDescription()
                            + "\n");
                } else if (c instanceof Deadline) {
                    fw.write("D |"
                            + c.getDone()
                            + "| "
                            + c.getDescription()
                            + " | "
                            + ((Deadline) c).getTime()
                            + "\n");
                } else if (c instanceof Event) {
                    fw.write("E |"
                            + c.getDone()
                            + "| "
                            + c.getDescription()
                            + " | "
                            + ((Event) c).getTime()
                            + "\n");

                } else {
                    // Empty Description
                }
            }
            fw.close();
        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
