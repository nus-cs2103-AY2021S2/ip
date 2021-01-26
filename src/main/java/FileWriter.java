import java.io.FileWriter;
import java.io.IOException;

class DukeFileWriter {

    static void writeFile(String input) throws DukeException{

        try {
            String path = "../tasks.txt";
            FileWriter fw = new FileWriter(path);

            fw.write(input);
            fw.close();

        } catch(IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
