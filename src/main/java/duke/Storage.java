package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/** Storage class saves most updated command list
 * to desired directories and files
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-02-22
 */
public class Storage {

    private String filePath = "./data/duke.txt";
    private Path path = Paths.get("./data/duke.txt");

    private String archiveFilePath = "./data/archive.txt";
    private Path archivePath = Paths.get("./data/archive.txt");


    public Storage() {
        new Storage(filePath, path);
    }

    /**
     * Constructor for storage
     *
     * @param filePath of desired directory and file name
     * @param path path of desired directory and file name
     */
    public Storage(String filePath, Path path) {
        this.filePath = filePath;
        this.path = path;
    }

    public String getFilePath() {
        return filePath;
    }

    public Path getPath() {
        return path;
    }

    public String getArchiveFilePath() {
        return archiveFilePath;
    }

    public Path getArchivePath() {
        return archivePath;
    }

    /**
     * Checks if existing data directory and duke.txt file
     * has been created, else create them with given file
     * paths from the root folder
     *
     * @param filePath desired path to save duke.txt
     * @param path desired path to save duke.txt in Path
     */
    public void checkPath(String filePath, Path path) {
        try {
            File file = new File(filePath);

            boolean isDir = Files.isDirectory(path);

            if (isDir) {
                //directory exists, proceed.
            } else {
                Files.createDirectories(Path.of(file.getParent()));
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Method saves the current Command list
     * into a text file
     *
     * @param filePath Filepath of text file to save to
     * @param path path of text file to save to
     * @param xs most updated command list to be saved
     */
    void save(String filePath, Path path, ArrayList<Command> xs) {
        try {
            checkPath(filePath, path);
            FileWriter fw = new FileWriter(filePath);

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
                }
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
