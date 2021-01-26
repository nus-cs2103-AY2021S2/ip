package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CopyPasta {
    public static void copyPasta(File copy, File paste) {
        try {
            //perform copypasta
            Scanner sc = new Scanner(copy);
            String string = "";
            while (sc.hasNext()) {
                string = string + sc.nextLine() + System.lineSeparator();
            }
            System.out.println(string);
            FileWriter fw = new FileWriter(paste);
            fw.write(string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
