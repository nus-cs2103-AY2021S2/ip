package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeOnlyIoException;

/**
 * Storage class that is used for the reading and writing of the list file
 */
public class Storage {

    /** Filewriter instance used to write into the file */
    private FileWriter writer = null;

    /** File instance that will be used to load file */
    private File file = null;

    /** Scanner instance that will be used to load file contents into Duke */
    private Scanner sc = null;

    /**
     * Storage class constructor to initialize the Storage object
     * @param filepath is the file path for loading of the file
     */
    public Storage(String filepath) throws DukeOnlyIoException {

        try {
            this.file = new File(filepath);
            if (!this.file.exists()) {
                this.file.getParentFile().mkdir();
                this.file.createNewFile();
            }
            sc = new Scanner(this.file);

        } catch (IOException err) {
            throw new DukeOnlyIoException();
        }
    }

    /**
     * Returns a arraylist of String objects that will be used to load the TaskList
     *
     * @return An ArrayList of String
     */
    public ArrayList<String> load() {

        ArrayList<String> list = new ArrayList<>();

        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            list.add(nextLine);
        }

        return list;
    }

    /**
     * Writes into the current TaskList into file
     *
     * @param list the TaskList
     * @throws DukeOnlyIoException when there is a IOException
     */
    public void write(TaskList list) throws DukeOnlyIoException {
        try {

            this.writer = new FileWriter(file, false);
            this.writer.write(list.getListToWrite());

            this.writer.close();

        } catch (IOException err) {
            throw new DukeOnlyIoException();
        }
    }
}
