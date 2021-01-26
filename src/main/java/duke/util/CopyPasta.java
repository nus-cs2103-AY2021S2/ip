package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CopyPasta {
    public static void copyPasta(File copy, File paste) {
        try {
            //remove all data from target paste file
            paste.delete();
            paste.createNewFile();
            //perform copypasta
            Scanner sc = new Scanner(copy);
            FileWriter fw = new FileWriter(paste);
            while (sc.hasNext()) {
                fw.write(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
