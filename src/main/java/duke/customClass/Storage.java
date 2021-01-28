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

    /**
     * Checks if there is a file which contains the saved data from a previous session.
     * If no file is found, then the program will create the file and continue as per normal.
     * If a file is found, then the tasks in the file will be returned as a string of Tasks at the start of the program.
     * @return A string of tasks in the save format, each separated by a newline.
     */
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
            File dir = new File("data");
            dir.mkdir();
            File file = new File("data/duke.tasks.txt");
            try {
                file.createNewFile();
            } catch (IOException err) {
                System.out.println("Error: " + err);
            }
            return "";
        }
    }

    /**
     * saves all the current tasks in the list as a string into a specified file that is stored locally.
     * @param list List containing all the tasks.
     */
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
