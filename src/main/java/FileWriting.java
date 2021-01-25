import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriting {

    private static void writeToFile(String filePath, ArrayList<Task> myList) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for(Task t: myList) {
            writer.write(t.toString() + System.lineSeparator());
        }
        writer.close();
    }

    public static void saveTaskList(ArrayList<Task> myList) {
        try {
            writeToFile("../CS2103_iP/data/duke.txt", myList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
