import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;

public class FileManager {
    File file;

    public FileManager(String pathname) {
        file = new File(pathname);
    }

    ListManager getList() throws FileNotFoundException, IOException {
            if (file.exists()) {

                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    System.out.println(sc.nextLine());
                }
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

        return new ListManager();
    }
}
