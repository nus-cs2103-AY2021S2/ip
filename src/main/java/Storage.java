import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;


public class Storage {
    private final static String PATHNAME = "C:/users/chian/Desktop/CS2103/ip/data/";
    private final static String FILENAME = "data.txt";

    public static void save(String tasks) {
        try {
            FileWriter fw = new FileWriter(PATHNAME + FILENAME);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            Ui.printMessage("Unable to save file!");
        }
    }

    public static void load(TaskList tl) {
        try {
            File f = new File(PATHNAME + FILENAME);
            f.createNewFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String strTask = sc.nextLine();
                String[] taskArray = strTask.split(" \\| ");
                Task t = null;
                switch (taskArray[0]) {
                case "T":
                    t = new ToDo(taskArray[2]);
                    break;
                case "E":
                    String[] timeArray = taskArray[3].split("-");
                    LocalDateTime ldtStart = Parser.parseFileDate(timeArray[0]);
                    LocalDateTime ldtEnd = Parser.parseFileDate(timeArray[1]);
                    t = new Event(taskArray[2], ldtStart, ldtEnd);
                    break;
                case "D":
                    LocalDateTime ldt = Parser.parseFileDate(taskArray[3]);
                    t = new Deadline(taskArray[2], ldt);
                    break;
                }
                if (t != null) {
                    if (taskArray[1].equals("X")) {
                        t = t.finishTask();
                    }
                    tl.add(t);
                }
            }
        } catch (Exception e) {
            Ui.printMessage("Unable to load file!");
        }
    }
}