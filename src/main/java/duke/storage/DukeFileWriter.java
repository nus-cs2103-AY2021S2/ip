package duke.storage;

import duke.exception.DukeException;

import java.io.FileWriter;
import java.io.IOException;

/**
 * DukeFileWriter deals with writing into the a file.
 */
class DukeFileWriter {

    /**
     * Writes input to the file that is located by the filepath.
     *
     * @param filePath The relative address of the file.
     * @param input Text to be written into the file.
     * @throws DukeException If an I/O error occurs.
     */
    void writeFile(String filePath, String input) throws DukeException {

        try {
            FileWriter fw = new FileWriter(filePath);

            fw.write(input);
            fw.close();

        } catch(IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}