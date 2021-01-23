package chatbot;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class DataFile {
    private String filePath;
    private File f;

    public DataFile(String filePath) {
        File f = new File(filePath);
        this.filePath = filePath;
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public String getFilePath() {
        return this.filePath;
    }
}
