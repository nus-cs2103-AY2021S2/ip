import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

class Storage {

    public static void createFile() throws java.io.IOException  {
        new File("./data").mkdirs();
        File textFile = new File("./data/duke.txt");
        textFile.delete();
        textFile.createNewFile();
    }

    public static void update(ArrayList<Task> taskList) {
        File textFile = new File("./data/duke.txt");
        try {
            createFile();
            FileWriter fileWriter = new FileWriter(textFile);
            for (Task t : taskList) {
                if (t instanceof ToDo) {
                    fileWriter.write("T | ");
                } else if (t instanceof Deadline) {
                    fileWriter.write("D | ");
                } else if (t instanceof Event) {
                    fileWriter.write("E | ");
                }
                if (t.isComplete()) {
                    fileWriter.write("1 | " + t.taskName);
                } else {
                    fileWriter.write("0 | " + t.taskName);
                }
                if (t instanceof Deadline) {
                    fileWriter.write(" | " + ((Deadline) t).getDate());
                }
                if (t instanceof Event) {
                    fileWriter.write(" | " + ((Event) t).getDate());
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }
}
