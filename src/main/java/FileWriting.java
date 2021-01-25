import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileWriting {

    private static void writeToFile(String filePath, ArrayList<Task> myList) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for(Task t: myList) {
            writer.write(t.toString() + System.lineSeparator());
//            String separator = " | ";
//            String type = t.getType();
//            String description = t.getDescription();
//            String status = (t.getStatus() ? "1" : "0");
//
//            if (type.equals("T")) {
//                writer.write(type + separator + status + separator + description + System.lineSeparator());
//            } else {
//                if (type.equals("D")) {
//                    String time = t.getTime();
//                    writer.write(type + separator + status + separator + description + System.lineSeparator());
//                } else {
//                    if (type.equals("T")) {
//                        String time = t.getTime();
//                        writer.write(type + separator + status + separator + description + System.lineSeparator());
//                    }
//                }
//            }
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
