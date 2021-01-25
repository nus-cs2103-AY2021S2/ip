import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private Scanner scannerFile;
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public List<String> loadFromFile() throws FileNotFoundException {
        File file = new File(filepath);
        this.scannerFile = new Scanner(file);
        List<String> txt = new ArrayList<>();
        while (this.scannerFile.hasNextLine()) {
            txt.add(this.scannerFile.nextLine());
        }
        return txt;
    }

    public void writeToFile(List<String> allStringTasks) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        String text = "";
        for (String s : allStringTasks) {
            text += s + "\n";
        }
        fw.write(text);
        fw.close();
    }
}
