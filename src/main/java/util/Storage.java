package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    public static String SAVE_PATH = "data/sweh.txt";
    private final File saveFile;

    public Storage(String first, String... more) throws IOException {
        Path savePath = Path.of(first, more);
        saveFile = new File(savePath.toString());
        (new File(savePath.getParent().toString())).mkdir();
        saveFile.createNewFile();
    }

    public static void writeToFile(String saveString) throws IOException {
        Path savePath = Path.of("data", "sweh.txt");
        File file = new File(savePath.toString());
        (new File(savePath.getParent().toString())).mkdir();
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write(saveString);
        fw.close();
    }

    public static File getFile() throws IOException {
        Path savePath = Path.of("data", "sweh.txt");
        File file = new File(savePath.toString());
        (new File(savePath.getParent().toString())).mkdir();
        file.createNewFile();
        return file;
    }

}
