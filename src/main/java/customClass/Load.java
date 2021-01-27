package customClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class Load {
    public String loadData(String path) throws IOException {
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
    }
}
