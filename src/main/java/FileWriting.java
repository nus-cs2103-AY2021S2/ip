import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriting {
    public static void writeToFile(String path, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Task task : list) {
            String s = "";
            if (task.type.equals("T")) {
                s = task.isDone ? "T" + " , " + "1" + " , " + task.description
                        : "T" + " , " + "0" + " , " + task.description;

            } else if (task.type.equals("E")) {
                Event myTask = (Event) task;
                s = task.isDone
                        ? "E" + " , " + "1" + " , " + task.description + " , " + myTask.dateAndDuration
                        : "E" + " , " + "0" + " , " + task.description + " , " + myTask.dateAndDuration;

            } else if (task.type.equals("D")) {
                Deadline myTask = (Deadline) task;
                s = task.isDone ? "D" + " , " + "1" + " , " + task.description + " , " + myTask.by
                        : "D" + " , " + "0" + " , " + task.description + " , " + myTask.by;

            }
            fw.write(s + System.lineSeparator());
        }
        fw.close();
    }

}
