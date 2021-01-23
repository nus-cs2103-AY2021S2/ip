package duke;

import duke.exceptions.DukeOnlyIOException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    /** Filewriter instance used to write into the file */
    private FileWriter writer = null;

    /** File instance that will be used to load file */
    private File file = null;

    /** Scanner instance that will be used to load file contents into Duke */
    private Scanner sc = null;

    public Storage(String filepath) throws DukeOnlyIOException {

        try {
            this.file = new File(filepath);
            sc = new Scanner(this.file);

            if (!this.file.exists()) {
                this.file.getParentFile().mkdir();
                this.file.createNewFile();
            }

        } catch (IOException err) {
            throw new DukeOnlyIOException();
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
     * @throws DukeOnlyIOException when there is a IOException
     */
    public void write(TaskList list) throws DukeOnlyIOException {
        try {

            this.writer = new FileWriter(file, false);
            this.writer.write(list.getListToWrite());

            this.writer.close();

        } catch (IOException err) {
            throw new DukeOnlyIOException();
        }
    }
}
