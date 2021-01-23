import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;
    public static ArrayList<String> storedList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static void loadTaskList(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            storedList.add(task);
        }
    }

    public static void storeTaskList(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for(int i = 0; i < Duke.taskList.size(); i++) {
                fw.write(Duke.taskList.get(i) + " \r\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
