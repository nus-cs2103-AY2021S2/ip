import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    /**
     * Responsible for storing the data in a local .txt file. Fetches data from the .txt file, parses them and sends the
     * Task out to the caller. Also receives new ArrayList, which it uses to rewrite  the .txt file.
     */

     private String filePath;

     Storage(String filePath) {
         this.filePath = filePath;
     }

     public List<Task> loadStorage() throws FileNotFoundException {
         List<Task> savedListOfTasks = new ArrayList<>();
         File fileSource = new File(filePath);
         Scanner scanner = new Scanner(fileSource);
         while(scanner.hasNextLine()) {
             String line = scanner.nextLine();
             Task t = Parser.parseTaskFromStoredFormat(line);
             savedListOfTasks.add(t);
         }
         return savedListOfTasks;
     }

     public void saveTasks(List<? extends Task> listOfTasks) throws IOException {
         FileWriter fw = new FileWriter(filePath);
         fw.write("");  // clear the file.
         fw.close();
         FileWriter fw_append = new FileWriter(filePath,true);
         for (Task t : listOfTasks) {
            fw_append.write(t.getSavedStringFormat() + "\n");
         }
         fw_append.close();
     }
}
