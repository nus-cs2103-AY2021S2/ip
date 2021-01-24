import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class FileManager {
    File file;

    public FileManager(String pathname) {
        file = new File(pathname);
    }

    ListManager getList() throws IOException, UnknownCommandException {
        if (file.exists()) {
            return new ListManager(this.convertFileToList());
        } else {
            this.createFile();
            return new ListManager();
        }
    }

    List<Task> convertFileToList() throws FileNotFoundException, UnknownCommandException {
        Scanner sc = new Scanner(file);
        List<Task> list = new ArrayList<Task>();

        // Splice the lines in the file
        // and generate the appropriate Tasks
        while (sc.hasNext()) {
            String item = sc.nextLine();
            String[] split = item.split("|", 0);

            switch (split[0]) {
            case "T":
                list.add(new Todo(split[2], split[1].equals("1")));
                break;
            case "D":
            case "E":
                list.add(new Deadline(split[2], split[1].equals("1"), split[3]));
                break;
            default:
                throw new UnknownCommandException();
            }
        }

        return list;
    }

    void createFile() throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }
}
