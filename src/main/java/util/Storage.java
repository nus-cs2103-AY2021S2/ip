package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private final File saveFile;

    public Storage(String first, String... more) throws IOException {
        Path savePath = Path.of(first, more);
        saveFile = new File(savePath.toString());
        (new File(savePath.getParent().toString())).mkdir();
        saveFile.createNewFile();
    }

    public void writeToFile(String saveString) throws IOException {
        FileWriter fw = new FileWriter(saveFile);
        fw.write(saveString);
        fw.close();
    }

    static String readSave() throws IOException {
        File file = getFile();
        Scanner sc = new Scanner(file);
        StringBuilder output = new StringBuilder();
        while (sc.hasNext()) {
            output.append(sc.nextLine());
        }
        sc.close();
        return output.toString();
    }
}
