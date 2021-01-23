import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    static File file;
    static Scanner sc;
    static FileWriter writer;

    public static void init() throws IOException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file = new File("data" + File.separatorChar + "data.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        sc = new Scanner(file);
    }

    public static TaskList loadToList() {
        TaskList list = new TaskList();
        while (Objects.requireNonNull(sc).hasNextLine()) {
            list.addJob(StringParser.loadData(sc.nextLine()));
        }
        return list;
    }

    public static void writeToData(TaskList list) {
        String saveData = "";
        for (int i = 0; i < list.getSize(); i++) {
            saveData = saveData.concat(StringParser.saveData(list.getJob(i)));
        }
        try {
            writer = new FileWriter(file);
            writer.write(saveData);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
