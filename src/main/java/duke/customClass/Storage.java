package duke.customClass;

import duke.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public String loadData() {
        try {
            File f = new File(path);
            Scanner sc = new Scanner(f);

            if (!f.exists()) {
                if (f.createNewFile())
                    System.out.println("File created");
                else
                    System.out.println("File already exists");
                return "";
            } else {
                String s = "";
                while(sc.hasNext()) {
                    String currLine = sc.nextLine();
                    s += currLine + "\n";
                }
                return s;
            }
        } catch (IOException e) {
            // Do not output error message, instead, create the dir and file
            // NOTE: THE PATH TO CREATE THE INIT FILE IS HARDCODED.
            File dir = new File("src/data");
            dir.mkdir();
            File file = new File("src/data/duke.tasks.txt");
            try {
                file.createNewFile();
            } catch (IOException err) {
                System.out.println("Error: " + err);
            }
            return "";
        }
    }

    public void save(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(path);

            for (Task task : list) {
                fw.write(task.saveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error :" + e);
        }
    }
}
