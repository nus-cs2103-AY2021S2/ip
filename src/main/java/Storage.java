import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public static List<ListItem> importData() {
        List<ListItem> importedList;
        importedList = new ArrayList<>();
        try {
            File f = new File("data/duke.txt");
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] result = s.nextLine().split("\\|");
                switch (result[0]) {
                    case "T":
                        importedList.add(new Todo(result[2], result[1] == "1"));
                        break;
                    case "D":
                        importedList.add(new Deadline(result[2], result[3], result[1] == "1"));
                        break;
                    case "E":
                        importedList.add(new Event(result[2], result[3], result[1] == "1"));
                        break;
                }
            }
            s.close();
            return importedList;
        }catch(FileNotFoundException ex){
            return new ArrayList<>();
        }
    }

    public static String writeData(String input){
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(input);
            fw.close();
            return "";
        }catch(IOException ex){
            return "IO error";
        }
    }
}
