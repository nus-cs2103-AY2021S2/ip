package duke;

import duke.exceptions.DukeOnlyIOException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private FileWriter writer;
    private File file;
    private Scanner sc;

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

    public ArrayList<String> load() {
        ArrayList<String> list = new ArrayList<>();
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            list.add(nextLine);
        }

        return list;
    }

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
