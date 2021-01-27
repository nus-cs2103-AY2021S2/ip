import java.io.FileWriter;
import java.io.IOException;

class DukeFileWriter {

    void writeFile(String filePath, String input) throws DukeException{

        try {
            FileWriter fw = new FileWriter(filePath);

            fw.write(input);
            fw.close();

        } catch(IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}